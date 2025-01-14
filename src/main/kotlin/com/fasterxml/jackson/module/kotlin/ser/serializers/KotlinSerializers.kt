package com.fasterxml.jackson.module.kotlin.ser.serializers

import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.BeanDescription
import com.fasterxml.jackson.databind.JavaType
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializationConfig
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.Serializers
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import com.fasterxml.jackson.module.kotlin.isUnboxableValueClass
import java.lang.reflect.Method
import java.lang.reflect.Modifier
import java.math.BigInteger

internal object SequenceSerializer : StdSerializer<Sequence<*>>(Sequence::class.java) {
    override fun serialize(value: Sequence<*>, gen: JsonGenerator, provider: SerializerProvider) {
        val materializedList = value.toList()
        provider.defaultSerializeValue(materializedList, gen)
    }
}

internal object UByteSerializer : StdSerializer<UByte>(UByte::class.java) {
    override fun serialize(value: UByte, gen: JsonGenerator, provider: SerializerProvider) =
        gen.writeNumber(value.toShort())
}

internal object UShortSerializer : StdSerializer<UShort>(UShort::class.java) {
    override fun serialize(value: UShort, gen: JsonGenerator, provider: SerializerProvider) =
        gen.writeNumber(value.toInt())
}

internal object UIntSerializer : StdSerializer<UInt>(UInt::class.java) {
    override fun serialize(value: UInt, gen: JsonGenerator, provider: SerializerProvider) =
        gen.writeNumber(value.toLong())
}

internal object ULongSerializer : StdSerializer<ULong>(ULong::class.java) {
    override fun serialize(value: ULong, gen: JsonGenerator, provider: SerializerProvider) {
        val longValue = value.toLong()
        when {
            longValue >= 0 -> gen.writeNumber(longValue)
            else -> gen.writeNumber(BigInteger(value.toString()))
        }
    }
}

// In accordance with kotlinx.serialization,
// value classes are unboxed and serialized if they do not have a specified serializer.
internal object ValueClassUnboxSerializer : StdSerializer<Any>(Any::class.java) {
    override fun serialize(value: Any, gen: JsonGenerator, provider: SerializerProvider) {
        val unboxed = value::class.java.getMethod("unbox-impl").invoke(value)

        if (unboxed == null) {
            provider.findNullValueSerializer(null).serialize(null, gen, provider)
            return
        }

        provider.findValueSerializer(unboxed::class.java).serialize(unboxed, gen, provider)
    }
}

// Class must be UnboxableValueClass.
private fun Class<*>.getStaticJsonValueGetter(): Method? = this.declaredMethods
    .find { method -> Modifier.isStatic(method.modifiers) && method.annotations.any { it is JsonValue } }

internal class ValueClassStaticJsonValueSerializer<T>(
    t: Class<T>,
    private val staticJsonValueGetter: Method
) : StdSerializer<T>(t) {
    private val unboxMethod: Method = t.getMethod("unbox-impl")

    override fun serialize(value: T, gen: JsonGenerator, provider: SerializerProvider) {
        val unboxed = unboxMethod.invoke(value)
        // As shown in the processing of the factory function, jsonValueGetter is always a static method.
        val jsonValue: Any? = staticJsonValueGetter.invoke(null, unboxed)
        jsonValue
            ?.let { provider.findValueSerializer(it::class.java).serialize(it, gen, provider) }
            ?: provider.findNullValueSerializer(null).serialize(null, gen, provider)
    }

    companion object {
        // `t` must be UnboxableValueClass.
        // If create a function with a JsonValue in the value class,
        // it will be compiled as a static method (= cannot be processed properly by Jackson),
        // so use a ValueClassSerializer.StaticJsonValue to handle this.
        fun createOrNull(t: Class<*>): StdSerializer<*>? =
            t.getStaticJsonValueGetter()?.let { ValueClassStaticJsonValueSerializer(t, it) }
    }
}

internal class KotlinSerializers : Serializers.Base() {
    override fun findSerializer(
        config: SerializationConfig?,
        type: JavaType,
        beanDesc: BeanDescription?
    ): JsonSerializer<*>? {
        val rawClass = type.rawClass

        return when {
            Sequence::class.java.isAssignableFrom(rawClass) -> SequenceSerializer
            UByte::class.java.isAssignableFrom(rawClass) -> UByteSerializer
            UShort::class.java.isAssignableFrom(rawClass) -> UShortSerializer
            UInt::class.java.isAssignableFrom(rawClass) -> UIntSerializer
            ULong::class.java.isAssignableFrom(rawClass) -> ULongSerializer
            // The priority of Unboxing needs to be lowered so as not to break the serialization of Unsigned Integers.
            rawClass.isUnboxableValueClass() -> ValueClassStaticJsonValueSerializer.createOrNull(rawClass)
                ?: ValueClassUnboxSerializer
            else -> null
        }
    }
}

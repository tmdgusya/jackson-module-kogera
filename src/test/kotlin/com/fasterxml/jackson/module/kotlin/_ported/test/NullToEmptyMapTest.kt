package com.fasterxml.jackson.module.kotlin._ported.test

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinFeature.NullToEmptyMap
import com.fasterxml.jackson.module.kotlin.kotlinModule
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestNullToEmptyMap {

    private data class TestClass(val foo: Map<String, Int>)

    @Test
    fun nonNullCaseStillWorks() {
        val mapper = createMapper()
        assertEquals(mapOf("bar" to 1), mapper.readValue("""{"foo": {"bar": 1}}""", TestClass::class.java).foo)
    }

    @Test
    fun shouldMapNullValuesToEmpty() {
        val mapper = createMapper()
        assertEquals(emptyMap<String, Int>(), mapper.readValue("{}", TestClass::class.java).foo)
        assertEquals(emptyMap<String, Int>(), mapper.readValue("""{"foo": null}""", TestClass::class.java).foo)
    }

    private fun createMapper(): ObjectMapper {
        return ObjectMapper().registerModule(kotlinModule { enable(NullToEmptyMap) })
    }
}

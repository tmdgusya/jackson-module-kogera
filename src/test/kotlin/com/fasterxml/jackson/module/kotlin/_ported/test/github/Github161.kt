package com.fasterxml.jackson.module.kotlin._ported.test.github

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.exc.MismatchedInputException
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail

class TestGithub161 {
    data class Foo(
        val foo: Int,
        val bar: Int
    )

    @Test
    fun testPrimitiveBeingZeroed() {
        val json = """{"foo":17}"""
        val objectMapper = jacksonObjectMapper().configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true)
        try {
            objectMapper.readValue(json, Foo::class.java)
            fail("Expected an error on the missing primitive value")
        } catch (ex: MismatchedInputException) {
            // success
        }
    }
}

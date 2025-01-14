package com.fasterxml.jackson.module.kotlin._ported.test.github

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestGithub131 {
    open class BaseClass(val name: String)

    class DerivedClass(name: String) : BaseClass(name)

    @Test
    fun testFailureCase() {
        val mapper = jacksonObjectMapper()
        val x = mapper.readValue<DerivedClass>("""{"name":"abc"}""")
        assertEquals(DerivedClass("abc").name, x.name)
    }
}

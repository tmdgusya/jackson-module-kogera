package com.fasterxml.jackson.module.kotlin._ported.test.github

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.test.github._ported.Github165JavaTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class TestGithub165 {
    class Github165KotlinTest(@JsonProperty("name") var showName: String) {
        var yearSetterCalled: Boolean = false
        var nameSetterCalled: Boolean = false

        var showYear: String? = null
            @JsonProperty("year")
            set(value) {
                yearSetterCalled = true
                field = value
            }

        var name: String
            @JsonIgnore get() = showName // Why define get: https://youtrack.jetbrains.com/issue/KT-6519

            @JsonProperty("name")
            set(value) {
                nameSetterCalled = true
                this.showName = value
            }
    }

    @Test
    fun testJsonSetterCalledKotlin() {
        val obj = jacksonObjectMapper().readValue<Github165KotlinTest>("""{"name":"Fred","year":"1942"}""")
        assertEquals("1942", obj.showYear)
        assertEquals("Fred", obj.showName)
        assertTrue(obj.yearSetterCalled)
        assertFalse(obj.nameSetterCalled)
    }

    @Test
    fun testJsonSetterCalledJava() {
        val obj = jacksonObjectMapper().readValue<Github165JavaTest>("""{"name":"Fred","year":"1942"}""")
        assertEquals("1942", obj.showYear)
        assertEquals("Fred", obj.showName)
        assertTrue(obj.yearSetterCalled)
        assertFalse(obj.nameSetterCalled)
    }
}

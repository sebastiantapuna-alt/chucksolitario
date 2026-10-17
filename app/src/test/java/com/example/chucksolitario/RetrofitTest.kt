package com.example.chucksolitario

import org.junit.Test
import okhttp3.HttpUrl.Companion.toHttpUrl

class RetrofitTest {
    @Test
    fun testUrl() {
        val baseUrl = "https://generativelanguage.googleapis.com/".toHttpUrl()
        val relative = "v1beta/models/gemini-1.5-flash:generateContent"
        val resolved = baseUrl.resolve(relative)
        println("RESOLVED_URL_TEST: $resolved")
    }
}

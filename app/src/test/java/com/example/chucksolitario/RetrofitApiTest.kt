package com.example.chucksolitario

import com.example.chucksolitario.data.RetrofitClient
import com.example.chucksolitario.data.ai.AiRequestDto
import com.example.chucksolitario.data.ai.RequestContentDto
import com.example.chucksolitario.data.ai.RequestPartDto
import com.example.chucksolitario.data.ai.toDomain
import kotlinx.coroutines.runBlocking
import org.junit.Test

class RetrofitApiTest {
    @Test
    fun testGenerateContentWithFlashLite() {
        runBlocking {
            val apiKey = BuildConfig.GEMINI_API_KEY
            val req = AiRequestDto(listOf(RequestContentDto(listOf(RequestPartDto("cuanto es 34 + 1")))))
            val res = RetrofitClient.instanceGemini.generateContent(
                apiKey = apiKey,
                request = req
            )
            val answer = res.toDomain()
            assert(answer.answer.isNotEmpty())
        }
    }
}

package com.example.chucksolitario.data.kanye

import com.example.chucksolitario.data.RetrofitClient

class KanyeQuoteRepository {
    suspend fun obtenerCItaKanyeAleatoria(): KanyeQuoteDto {
        return RetrofitClient.instanceKanye.getRandomKanyeQuote()
    }
}
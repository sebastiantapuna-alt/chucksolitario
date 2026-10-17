package com.example.chucksolitario.data.kanye

import com.example.chucksolitario.data.RetrofitClient

class KanyeQuoteRepository {
    suspend fun obtenerCItaKanyeAleatoria(): KanyeQuote {
        return RetrofitClient.instanceKanye.getRandomKanyeQuote()
    }
}
package com.example.chucksolitario.data.kanye

import retrofit2.http.GET

interface KanyeQuoteApi {
    @GET ("/")
    suspend fun getRandomKanyeQuote(): KanyeQuote
}
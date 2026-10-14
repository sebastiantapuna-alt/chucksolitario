package com.example.chucksolitario.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface ChuckApiService {
    @GET("jokes/random")
    suspend fun getRandomJoke(
        @Query("category") category: String? = null
    ): JokeDto
}

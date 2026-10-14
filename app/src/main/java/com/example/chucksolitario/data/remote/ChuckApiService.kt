package com.example.chucksolitario.data.remote

import retrofit2.http.GET

interface ChuckApiService {
    @GET("jokes/random")
    suspend fun getRandomJoke(): JokeDto
}

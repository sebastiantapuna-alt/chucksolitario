package com.example.chucksolitario.data.chucknorris

import retrofit2.http.GET

interface ChuckNorrisApi {
    @GET("jokes/random")
    suspend fun obtenerChiste(): JokeDto
}

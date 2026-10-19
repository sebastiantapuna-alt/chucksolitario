package com.example.chucksolitario.data

import retrofit2.http.GET
import retrofit2.http.Query

interface FactApi {
    @GET("api/v2/facts/random")
    suspend fun obtenerDatoInutil(
        @Query("language") language: String = "es"
    ): FactDto
}

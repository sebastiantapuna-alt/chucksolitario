package com.example.chucksolitario.data.time

import retrofit2.http.GET
import retrofit2.http.Query

interface TimeApi {
    @GET("api/v1/time/current/zone")
    suspend fun obtenerHora(
        @Query("timeZone") timeZone: String
    ): TimeDto
}

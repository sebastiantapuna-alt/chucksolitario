package com.example.chucksolitario.data

import retrofit2.http.GET
import retrofit2.http.Query

interface TimeApi {
    // Consulta de hora actual por zona horaria (ej: America/La_Paz)
    @GET("api/v1/time/current/zone")
    suspend fun obtenerHora(
        @Query("timeZone") timeZone: String
    ): TimeDto
}

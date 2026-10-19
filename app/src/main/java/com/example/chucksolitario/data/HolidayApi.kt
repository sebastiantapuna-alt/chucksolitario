package com.example.chucksolitario.data

import retrofit2.http.GET
import retrofit2.http.Path

interface HolidayApi {
    // Usamos @Path para poder cambiar el año (2026) y el país (BO) dinámicamente desde el repositorio
    // OJO: La API de Nager devuelve una LISTA de feriados (un arreglo en JSON "[ ]"), por eso usamos List<HolidayDto>
    @GET("api/v3/PublicHolidays/{year}/{countryCode}")
    suspend fun obtenerFeriados(
        @Path("year") year: String,
        @Path("countryCode") countryCode: String
    ): List<HolidayDto>
}
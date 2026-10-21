package com.example.chucksolitario.data.holiday

import retrofit2.http.GET
import retrofit2.http.Path

interface HolidayApi {
    @GET("api/v3/PublicHolidays/{year}/{countryCode}")
    suspend fun obtenerFeriados(
        @Path("year") year: String,
        @Path("countryCode") countryCode: String
    ): List<HolidayDto>
}

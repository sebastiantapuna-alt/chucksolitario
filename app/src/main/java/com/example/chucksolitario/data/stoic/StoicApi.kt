package com.example.chucksolitario.data.stoic

import com.example.chucksolitario.data.PokeDto;
import retrofit2.http.GET;

interface StoicApi {

    @GET("stoic-quote")
        suspend fun obtenerCita(): StoicResponseDto
}
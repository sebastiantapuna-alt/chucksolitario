package com.example.chucksolitario.data

import com.example.chucksolitario.models.Holiday

class HolidayRepository {
    suspend fun obtenerListaFeriados(year: String, countryCode: String): List<Holiday> {
        // 1. Llamamos a Retrofit
        val listaDtos = RetrofitClient.holidayInstance.obtenerFeriados(year, countryCode)
        // 2. Mapeamos TODA la lista a nuestros modelos puros y la retornamos
        return listaDtos.map { it.toDomain() }
    }
}
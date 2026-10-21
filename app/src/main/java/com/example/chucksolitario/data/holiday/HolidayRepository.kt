package com.example.chucksolitario.data.holiday

import com.example.chucksolitario.data.chucknorris.RetrofitClient
import com.example.chucksolitario.models.Holiday

class HolidayRepository {
    suspend fun obtenerListaFeriados(year: String, countryCode: String): List<Holiday> {
        val listaDtos = RetrofitClient.holidayInstance.obtenerFeriados(year, countryCode)
        return listaDtos.map { it.toDomain() }
    }
}

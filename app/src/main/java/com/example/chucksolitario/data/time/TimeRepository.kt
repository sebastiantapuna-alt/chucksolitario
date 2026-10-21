package com.example.chucksolitario.data.time

import com.example.chucksolitario.data.chucknorris.RetrofitClient
import com.example.chucksolitario.models.TimeInfo

class TimeRepository {
    suspend fun obtenerHoraActual(area: String, location: String): TimeInfo {
        val timeZone = "$area/$location"
        val timeDto = RetrofitClient.timeInstance.obtenerHora(timeZone)
        return timeDto.toDomain()
    }
}

package com.example.chucksolitario.data.time

import com.example.chucksolitario.models.TimeInfo

fun TimeDto.toDomain(): TimeInfo {
    return TimeInfo(
        datetime = this.datetime ?: "Hora no disponible",
        timezone = this.timezone ?: "Zona no disponible"
    )
}

package com.example.chucksolitario.data

import com.example.chucksolitario.models.TimeInfo

/**
 * Transforma un TimeDto (de la API) a un TimeInfo (de la App).
 */
fun TimeDto.toDomain(): TimeInfo {
    return TimeInfo(
        datetime = this.datetime ?: "Hora no disponible",
        timezone = this.timezone ?: "Zona no disponible"
    )
}
package com.example.chucksolitario.data

import com.example.chucksolitario.models.Holiday

/**
 * Un Mapeador (Mapper) que transforma un HolidayDto (el que viene de la API)
 * a un objeto de Dominio (nuestro Holiday.kt).
 */
fun HolidayDto.toDomain(): Holiday {
    return Holiday(
        date = this.date ?: "Fecha desconocida", // Si la API no manda fecha, ponemos este texto por defecto
        localName = this.localName ?: "Feriado desconocido"
    )
}
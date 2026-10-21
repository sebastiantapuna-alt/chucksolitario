package com.example.chucksolitario.data.holiday

import com.example.chucksolitario.models.Holiday

fun HolidayDto.toDomain(): Holiday {
    return Holiday(
        date = this.date ?: "Fecha desconocida",
        localName = this.localName ?: "Feriado desconocido"
    )
}

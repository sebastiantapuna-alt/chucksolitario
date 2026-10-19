package com.example.chucksolitario.data

import com.google.gson.annotations.SerializedName

/**
 * Representa la respuesta de la API de Nager.Date.
 */
data class HolidayDto(
    @SerializedName("date") val date: String?,
    @SerializedName("localName") val localName: String?
)
package com.example.chucksolitario.data

import com.google.gson.annotations.SerializedName

/**
 * DTO que mapea la respuesta JSON de Useless Facts API.
 */
data class FactDto(
    @SerializedName("id") val id: String?,
    @SerializedName("text") val text: String?,
    @SerializedName("source") val source: String?,
    @SerializedName("source_url") val sourceUrl: String?,
    @SerializedName("language") val language: String?,
    @SerializedName("permalink") val permalink: String?
)

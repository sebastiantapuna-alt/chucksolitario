package com.example.chucksolitario.data

import com.google.gson.annotations.SerializedName

/**
 * DTO (Data Transfer Object)
 * Representa exactamente la estructura JSON que responde la API de Chuck Norris.
 * Usamos @SerializedName para indicar el nombre exacto de la llave en el JSON.
 * Aunque en Kotlin usemos nombres como "iconUrl" (camelCase),
 * Gson sabrá que debe buscar "icon_url" en el JSON original.
 */
data class JokeDto(
    @SerializedName("id") val id: String,
    @SerializedName("value") val value: String,
    @SerializedName("url") val url: String,
    @SerializedName("icon_url") val iconUrl: String,
    @SerializedName("categories") val categories: List<String>?,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String
)
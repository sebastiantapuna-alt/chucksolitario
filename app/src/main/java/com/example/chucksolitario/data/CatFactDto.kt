package com.example.chucksolitario.data

import com.google.gson.annotations.SerializedName

/**
 * DTO (Data Transfer Object) para la Cat Fact API (https://catfact.ninja/fact)
 */
data class CatFactDto(
    @SerializedName("fact") val fact: String?,
    @SerializedName("length") val length: Int?
)

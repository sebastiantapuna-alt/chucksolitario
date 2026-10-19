package com.example.chucksolitario.data

import com.google.gson.annotations.SerializedName

/**
 * DTO (Data Transfer Object) para la Dog CEO API (https://dog.ceo/api/breeds/image/random)
 */
data class DogDto(
    @SerializedName("message") val imageUrl: String?,
    @SerializedName("status") val status: String?
)

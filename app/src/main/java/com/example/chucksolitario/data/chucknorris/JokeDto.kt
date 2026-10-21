package com.example.chucksolitario.data.chucknorris

import com.google.gson.annotations.SerializedName

data class JokeDto(
    @SerializedName("id") val id: String,
    @SerializedName("value") val value: String,
    @SerializedName("url") val url: String,
    @SerializedName("icon_url") val iconUrl: String,
    @SerializedName("categories") val categories: List<String>?,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String
)

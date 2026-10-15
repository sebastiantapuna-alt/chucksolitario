package com.example.chucksolitario.data.remote

import com.google.gson.annotations.SerializedName

data class JokeDto(
    @SerializedName("categories") val categories: List<String> = emptyList(),
    @SerializedName("created_at") val createdAt: String = "",
    @SerializedName("icon_url") val iconUrl: String = "",
    val id: String = "",
    @SerializedName("updated_at") val updatedAt: String = "",
    val url: String = "",
    val value: String = ""
)

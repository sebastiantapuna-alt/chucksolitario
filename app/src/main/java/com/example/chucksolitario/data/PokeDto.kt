package com.example.chucksolitario.data

import com.google.gson.annotations.SerializedName

data class PokeDto (
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String,
)
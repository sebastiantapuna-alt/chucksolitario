package com.example.chucksolitario.model

data class Joke(
    val categorias: List<String>,
    val creacion: String,
    val iconUrl: String,
    val id: Int,
    val subido: String,
    val url: String,
    val valor:String
)
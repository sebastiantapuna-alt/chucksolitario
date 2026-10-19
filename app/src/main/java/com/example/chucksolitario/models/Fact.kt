package com.example.chucksolitario.models

/**
 * Modelo de dominio para representar un dato curioso/inútil.
 */
data class Fact(
    val id: String,
    val text: String,
    val source: String
)

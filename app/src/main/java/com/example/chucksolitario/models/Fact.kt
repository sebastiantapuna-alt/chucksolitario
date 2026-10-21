package com.example.chucksolitario.models

import com.example.chucksolitario.data.local.FavoritoEntity

/**
 * Modelo de dominio para representar un dato curioso/inútil.
 */
data class Fact(
    val id: String,
    val text: String,
    val source: String
) {
    /**
     * Convierte el modelo de dominio Fact a la entidad de Room (FavoritoEntity)
     * para guardarlo en la base de datos al marcarlo como favorito.
     */
    fun toEntity(): FavoritoEntity = FavoritoEntity(
        id = id,
        text = text,
        source = source
    )
}

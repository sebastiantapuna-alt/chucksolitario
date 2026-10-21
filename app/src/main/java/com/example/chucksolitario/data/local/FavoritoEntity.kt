package com.example.chucksolitario.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidad de Room que representa la tabla "favoritos" para la API Fact.
 */
@Entity(tableName = "favoritos")
data class FavoritoEntity(
    @PrimaryKey val id: String,
    val text: String,
    val source: String,
    val agregadoEn: Long = System.currentTimeMillis()
)

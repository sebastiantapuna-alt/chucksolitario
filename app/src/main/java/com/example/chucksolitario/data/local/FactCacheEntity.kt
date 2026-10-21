package com.example.chucksolitario.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidad de caché en Room para almacenar los datos inútiles descargados.
 */
@Entity(tableName = "fact_cache")
data class FactCacheEntity(
    @PrimaryKey val id: String,
    val text: String,
    val source: String
)

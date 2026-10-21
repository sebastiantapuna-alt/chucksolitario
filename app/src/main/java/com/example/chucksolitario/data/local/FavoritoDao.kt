package com.example.chucksolitario.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Interfaz Data Access Object (DAO) para definir las operaciones de la tabla "favoritos".
 */
@Dao
interface FavoritoDao {
    @Query("SELECT * FROM favoritos ORDER BY agregadoEn DESC")
    fun observarFavoritos(): Flow<List<FavoritoEntity>>

    // Solo los ids: basta para saber si un elemento está marcado como favorito.
    @Query("SELECT id FROM favoritos")
    fun observarIdsFavoritos(): Flow<List<String>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(favorito: FavoritoEntity)

    @Query("DELETE FROM favoritos WHERE id = :id")
    suspend fun eliminarPorId(id: String)
}

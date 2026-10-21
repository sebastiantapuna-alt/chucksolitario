package com.example.chucksolitario.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * DAO para realizar operaciones sobre la tabla de caché "fact_cache".
 */
@Dao
interface FactCacheDao {
    @Query("SELECT * FROM fact_cache")
    fun observarTodos(): Flow<List<FactCacheEntity>>

    @Query("SELECT COUNT(*) FROM fact_cache")
    suspend fun contar(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarTodos(facts: List<FactCacheEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(fact: FactCacheEntity)
}

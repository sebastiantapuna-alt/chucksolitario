package com.example.chucksolitario.data

import com.example.chucksolitario.data.fact.toDomain
import com.example.chucksolitario.data.local.FavoritoDao
import com.example.chucksolitario.models.Fact
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Repositorio de Favoritos que interactúa con la base de datos local (Room)
 * a través de FavoritoDao.
 */
class FavoritosRepository(private val dao: FavoritoDao) {

    /**
     * Devuelve un flujo (Flow) de la lista de favoritos convertidos al modelo Fact.
     */
    fun observarFavoritos(): Flow<List<Fact>> =
        dao.observarFavoritos().map { entidades -> entidades.map { it.toDomain() } }

    /**
     * Devuelve un conjunto (Set) de IDs favoritos para verificar eficientemente si un Fact es favorito.
     */
    fun observarIds(): Flow<Set<String>> =
        dao.observarIdsFavoritos().map { ids -> ids.toSet() }

    /**
     * Agrega un Fact a los favoritos en la base de datos.
     */
    suspend fun agregar(fact: Fact) = dao.insertar(fact.toEntity())

    /**
     * Elimina un favorito por su ID.
     */
    suspend fun quitar(id: String) = dao.eliminarPorId(id)
}

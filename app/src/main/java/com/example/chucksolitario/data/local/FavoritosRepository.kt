package com.example.chucksolitario.data.local

import com.example.chucksolitario.data.aEntidad
import com.example.chucksolitario.data.aResumen
import com.example.chucksolitario.models.KanyeQuoteResumen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class FavoritosRepository(private val dao: FavoritoDao) {
    fun observarFavoritos(): Flow<List<KanyeQuoteResumen>> =
        dao.observarFavoritos().map { entidades -> entidades.map { it.aResumen() } }
    fun observarIds(): Flow<Set<Int>> =
        dao.observarIdsFavoritos().map { ids -> ids.toSet() }
    suspend fun agregar(cita: KanyeQuoteResumen) = withContext(Dispatchers.IO) {
        dao.insertar(cita.aEntidad())
    }
    suspend fun quitar(id: Int) = withContext(Dispatchers.IO) {
        dao.eliminarPorId(id)
    }
}
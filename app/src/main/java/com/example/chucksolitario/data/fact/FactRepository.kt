package com.example.chucksolitario.data.fact

import com.example.chucksolitario.data.chucknorris.RetrofitClient
import com.example.chucksolitario.data.local.FactCacheDao
import com.example.chucksolitario.models.Fact
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FactRepository(
    private val cacheDao: FactCacheDao,
    private val api: FactApi = RetrofitClient.factInstance
) {
    /**
     * La lista que ve el usuario: lo que haya guardado en Room, con o sin internet.
     */
    fun observarFacts(): Flow<List<Fact>> =
        cacheDao.observarTodos().map { entidades -> entidades.map { it.toDomain() } }

    /**
     * Verifica si existen datos guardados en la caché local.
     */
    suspend fun hayFactsGuardados(): Boolean = cacheDao.contar() > 0

    /**
     * Descarga un nuevo dato inútil de la API y lo guarda en Room (Caché).
     */
    suspend fun cargarFactAleatorio(): Fact {
        val factDto = api.obtenerDatoInutil(language = "es")
        val fact = factDto.toDomain()
        cacheDao.insertar(fact.toCacheEntity())
        return fact
    }
}

package com.example.chucksolitario.data.stoic

import com.example.chucksolitario.data.RetrofitClient
import com.example.chucksolitario.models.StoicQuote

class StoicRepository {
    suspend fun obtenerCitaAleatoria(): StoicQuote {
        // 1. Obtenemos el Wrapper
        val response = RetrofitClient.instance2.obtenerCita()

        // 2. Extraemos el objeto interno "data" (que es el StoicDto) y lo mapeamos
        return response.data.toDomain()
    }
}
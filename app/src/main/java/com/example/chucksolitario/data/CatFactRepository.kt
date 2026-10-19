package com.example.chucksolitario.data

import com.example.chucksolitario.models.CatFact

/**
 * Repositorio para la Cat Fact API.
 * Intermediario entre Retrofit y CatFactViewModel.
 */
class CatFactRepository {

    suspend fun obtenerDatoGatoAleatorio(): CatFact {
        val catFactDto = RetrofitClient.catFactApi.obtenerDatoGato()
        return catFactDto.toDomain()
    }
}

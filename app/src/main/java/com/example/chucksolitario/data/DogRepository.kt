package com.example.chucksolitario.data

import com.example.chucksolitario.models.DogImage

/**
 * Repositorio para la Dog CEO API.
 * Intermediario entre Retrofit y DogViewModel.
 */
class DogRepository {

    suspend fun obtenerImagenPerroAleatoria(): DogImage {
        val dogDto = RetrofitClient.dogApi.obtenerImagenPerro()
        return dogDto.toDomain()
    }
}

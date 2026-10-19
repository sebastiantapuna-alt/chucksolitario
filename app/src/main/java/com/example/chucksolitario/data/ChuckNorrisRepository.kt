package com.example.chucksolitario.data

import com.example.chucksolitario.models.Joke

/**
 * Repositorio para la API de Chuck Norris.
 * Intermediario entre Retrofit y el ViewModel.
 */
class ChuckNorrisRepository {

    suspend fun obtenerChisteAleatorio(): Joke {
        val jokeDto = RetrofitClient.chuckNorrisApi.obtenerChiste()
        return jokeDto.toDomain()
    }
}

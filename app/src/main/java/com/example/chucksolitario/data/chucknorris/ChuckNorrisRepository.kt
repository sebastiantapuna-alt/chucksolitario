package com.example.chucksolitario.data.chucknorris

import com.example.chucksolitario.models.Joke

class ChuckNorrisRepository {
    suspend fun obtenerChisteAleatorio(): Joke {
        val jokeDto = RetrofitClient.instance.obtenerChiste()
        return jokeDto.toDomain()
    }
}

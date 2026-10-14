package com.example.chucksolitario.data

import com.example.chucksolitario.models.Joke

/**
 * El Repositorio (Repository) es el intermediario entre la API (Retrofit) y el ViewModel.
 * Su único trabajo es ir a buscar los datos y devolverlos limpios usando el Mapeador.
 * De esta manera, el ViewModel no sabe si los datos vienen de Retrofit, Firebase o una Base de datos.
 */
class ChuckNorrisRepository {
    
    // Función "suspend" porque es una operación de red que toma tiempo (asíncrona).
    suspend fun obtenerChisteAleatorio(): Joke {
        // 1. Llamamos a la API a través de nuestra instancia de RetrofitClient
        // Esto nos devuelve un objeto sucio tal como viene del JSON (JokeDto)
        val jokeDto = RetrofitClient.instance.obtenerChiste()
        
        // 2. Usamos nuestra función de extensión .toDomain() creada en Mapeadores.kt
        // Esto transforma el JokeDto a un Joke (Modelo puro) y lo retorna.
        return jokeDto.toDomain()
    }
}
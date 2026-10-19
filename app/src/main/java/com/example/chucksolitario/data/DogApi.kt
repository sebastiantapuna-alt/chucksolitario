package com.example.chucksolitario.data

import retrofit2.http.GET

/**
 * Interface Retrofit para la API de imágenes aleatorias de perros
 */
interface DogApi {

    @GET("breeds/image/random")
    suspend fun obtenerImagenPerro(): DogDto

}

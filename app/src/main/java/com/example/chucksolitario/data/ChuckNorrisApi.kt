package com.example.chucksolitario.data

import retrofit2.http.GET

/**
 * Define los endpoints de la API de Chuck Norris. 
 * Retrofit genera el código de fondo automáticamente.
 */
interface ChuckNorrisApi {
    
    // Al añadir "jokes/random" a nuestra BASE_URL de RetrofitClient,
    // Llamaremos exactamente a: https://api.chucknorris.io/jokes/random
    @GET("jokes/random")
    suspend fun obtenerChiste(): JokeDto

}
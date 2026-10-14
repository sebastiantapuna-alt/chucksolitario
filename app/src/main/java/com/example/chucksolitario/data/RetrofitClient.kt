package com.example.chucksolitario.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/** 
 * Unica instancia de Retrofit de la app (patron singleton con `object`). 
 * Nos conectará a la web.
 */
object RetrofitClient {

    // La URL base debe terminar en "/" para que Retrofit pueda concatenar las rutas.
    private const val BASE_URL = "https://api.chucknorris.io/"

    // Cliente HTTP para interceptar y ver los logs (la respuesta JSON) en la pestaña de Logcat
    private val clienteHttp: OkHttpClient = OkHttpClient.Builder()
        // Level.BODY nos permite ver todo el JSON que descarga en el Logcat
        .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
        .build()

    // Inicialización "lazy" (perezosa): Solo se crea cuando se la llama por primera vez
    val instance: ChuckNorrisApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL) // Le pasamos la url
            .client(clienteHttp) // Le pasamos el interceptor
            .addConverterFactory(GsonConverterFactory.create()) // El conversor mágico que pasa de JSON a Kotlin
            .build()
            .create(ChuckNorrisApi::class.java) // Crea la implementación de nuestra Interfaz
    }
}
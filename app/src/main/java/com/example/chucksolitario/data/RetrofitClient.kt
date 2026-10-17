package com.example.chucksolitario.data

import com.example.chucksolitario.data.stoic.StoicApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.jvm.java

/** 
 * Unica instancia de Retrofit de la app (patron singleton con `object`). 
 * Nos conectará a la web.
 */
object RetrofitClient {

    // La URL base debe terminar en "/" para que Retrofit pueda concatenar las rutas.
    private const val BASE_URL = "https://api.chucknorris.io/"
    private const val BASE_URL2 = "https://pokeapi.co/api/v2/"
    private const val BASE_URL3 = "https://stoic.tekloon.net/"



    // Cliente HTTP para interceptar y ver los logs (la respuesta JSON) en la pestaña de Logcat
    private val clienteHttp: OkHttpClient = OkHttpClient.Builder()
        // Level.BODY nos permite ver t0do el JSON que descarga en el Logcat
        .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
        .build()

    // Inicialización "lazy" (perezosa): Solo se crea cuando se la llama por primera vez
    val instance: ChuckNorrisApi by lazy {
        Retrofit.Builder() // builder es un patron de diseño de codigo
            .baseUrl(BASE_URL) // Le pasamos la url de Chuck Norris (BASE_URL)
            .client(clienteHttp) // Le pasamos el interceptor
            .addConverterFactory(GsonConverterFactory.create()) // El conversor mágico que pasa de JSON a Kotlin
            .build() //constructor del objecto Retrofit
            .create(ChuckNorrisApi::class.java) // Crea la implementación de nuestra Interfaz
    }

    val instance2: StoicApi by lazy {
        Retrofit.Builder() // builder es un patron de diseño de codigo
            .baseUrl(BASE_URL3) // Le pasamos la url
            .client(clienteHttp) // Le pasamos el interceptor
            .addConverterFactory(GsonConverterFactory.create()) // El conversor mágico que pasa de JSON a Kotlin
            .build() //constructor del objecto Retrofit
            .create(StoicApi::class.java) // Crea la implementación de nuestra Interfaz

    }

}
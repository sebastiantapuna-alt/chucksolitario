package com.example.chucksolitario.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/** 
 * Cliente Retrofit centralizado (patrón Singleton con `object`).
 * Mantiene las instancias de las 3 APIs integradas en la app.
 */
object RetrofitClient {

    private val clienteHttp: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
        .build()

    // 1. Chuck Norris API
    private const val CHUCK_NORRIS_BASE_URL = "https://api.chucknorris.io/"
    val chuckNorrisApi: ChuckNorrisApi by lazy {
        Retrofit.Builder()
            .baseUrl(CHUCK_NORRIS_BASE_URL)
            .client(clienteHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ChuckNorrisApi::class.java)
    }

    // 2. Cat Fact API
    private const val CAT_FACT_BASE_URL = "https://catfact.ninja/"
    val catFactApi: CatFactApi by lazy {
        Retrofit.Builder()
            .baseUrl(CAT_FACT_BASE_URL)
            .client(clienteHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CatFactApi::class.java)
    }

    // 3. Dog CEO API
    private const val DOG_BASE_URL = "https://dog.ceo/api/"
    val dogApi: DogApi by lazy {
        Retrofit.Builder()
            .baseUrl(DOG_BASE_URL)
            .client(clienteHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DogApi::class.java)
    }

    // Mantenemos `instance` para compatibilidad con código previo
    val instance: ChuckNorrisApi
        get() = chuckNorrisApi
}

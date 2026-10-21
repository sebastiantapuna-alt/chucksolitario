package com.example.chucksolitario.data.chucknorris

import com.example.chucksolitario.data.fact.FactApi
import com.example.chucksolitario.data.holiday.HolidayApi
import com.example.chucksolitario.data.time.TimeApi
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/** 
 * Única instancia de Retrofit de la app (patrón singleton con `object`). 
 * Configurada con cabeceras estándar para conexiones estables a todas las APIs.
 */
object RetrofitClient {

    private const val BASE_URL_CHUCK = "https://api.chucknorris.io/"
    private const val BASE_URL_HOLIDAY = "https://date.nager.at/"
    private const val BASE_URL_TIME = "https://timeapi.io/"
    private const val BASE_URL_FACT = "https://uselessfacts.jsph.pl/"

    // Cliente HTTP con User-Agent estándar
    private val clienteHttp: OkHttpClient = OkHttpClient.Builder()
        .protocols(listOf(Protocol.HTTP_1_1))
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .header("User-Agent", "Mozilla/5.0 (Android 12; Mobile; rv:124.0) Gecko/124.0 Firefox/124.0")
                .header("Accept", "application/json")
                .build()
            chain.proceed(request)
        }
        .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC })
        .retryOnConnectionFailure(true)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    // Instancia de la API de Chuck Norris
    val instance: ChuckNorrisApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_CHUCK)
            .client(clienteHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ChuckNorrisApi::class.java)
    }

    // Instancia de la API de feriados (Nager.Date)
    val holidayInstance: HolidayApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_HOLIDAY)
            .client(clienteHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HolidayApi::class.java)
    }

    // Instancia de la API de Tiempo (TimeAPI.io)
    val timeInstance: TimeApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_TIME)
            .client(clienteHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TimeApi::class.java)
    }

    // Instancia de la API de Datos Inútiles (Useless Facts API)
    val factInstance: FactApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_FACT)
            .client(clienteHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FactApi::class.java)
    }
}

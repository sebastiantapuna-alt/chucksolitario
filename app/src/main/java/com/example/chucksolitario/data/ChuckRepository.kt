package com.example.chucksolitario.data

import com.example.chucksolitario.data.remote.ChuckApiService
import com.example.chucksolitario.data.remote.RetrofitClient
import com.example.chucksolitario.model.Joke

class ChuckRepository {
    private val api: ChuckApiService = RetrofitClient.apiService

    suspend fun obtenerChiste(categoria: String?): Result<Joke> {
        val cat = if (categoria == "random") null else categoria
        return runCatching {
            api.obtenerJoke(category = cat).aResumen()
        }
    }
}

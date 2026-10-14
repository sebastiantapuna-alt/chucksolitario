package com.example.chucksolitario.data

import com.example.chucksolitario.data.remote.ChuckApiService
import com.example.chucksolitario.data.remote.RetrofitClient

class ChuckRepository {
    private val api: ChuckApiService = RetrofitClient.apiService


}
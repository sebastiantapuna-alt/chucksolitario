package com.example.chucksolitario.data

import com.example.chucksolitario.models.Fact

class FactRepository {
    suspend fun obtenerDatoAleatorio(): Fact {
        val factDto = RetrofitClient.factInstance.obtenerDatoInutil(language = "es")
        return factDto.toDomain()
    }
}

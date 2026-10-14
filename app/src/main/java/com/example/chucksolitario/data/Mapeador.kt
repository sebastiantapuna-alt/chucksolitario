package com.example.chucksolitario.data

import com.example.chucksolitario.data.remote.JokeDto
import com.example.chucksolitario.model.Joke

fun JokeDto.aResumen(): Joke{
    return Joke(
        categorias = categories,
        creacion = createdAt,
        iconUrl = iconUrl,
        id = id,
        subido = updatedAt,
        url = url,
        valor = value
    )
}
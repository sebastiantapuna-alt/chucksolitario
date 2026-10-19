package com.example.chucksolitario.data

import com.example.chucksolitario.models.CatFact
import com.example.chucksolitario.models.DogImage
import com.example.chucksolitario.models.Joke

/**
 * Mapeadores (Mappers):
 * Transforman objetos DTO (respuesta JSON cruda) a objetos de Dominio (modelos puros).
 * Con esto "limpiamos" los datos antes de que lleguen a la interfaz de usuario.
 */

fun JokeDto.toDomain(): Joke {
    return Joke(
        id = this.id,
        value = this.value,
        url = this.url,
        icon_url = this.iconUrl,
        categories = this.categories ?: emptyList(),
        created_at = this.createdAt,
        updated_at = this.updatedAt
    )
}

fun CatFactDto.toDomain(): CatFact {
    return CatFact(
        fact = this.fact ?: "Sin información sobre gatos disponible.",
        length = this.length ?: 0
    )
}

fun DogDto.toDomain(): DogImage {
    return DogImage(
        imageUrl = this.imageUrl ?: "",
        status = this.status ?: "unknown"
    )
}

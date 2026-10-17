package com.example.chucksolitario.data

import com.example.chucksolitario.models.Joke

/**
 * Un Mapeador (Mapper) es una función o clase que transforma un objeto DTO (el que viene de la API)
 * a un objeto de Dominio (nuestro Joke.kt que usamos en la app).
 * 
 * ¿Por qué hacemos esto?
 * Porque a veces las APIs devuelven valores nulos o nombres de variables raros. 
 * Con el mapeador "limpiamos" los datos antes de que lleguen a nuestra interfaz de usuario.
 */
fun JokeDto.toDomain(): Joke {
    return Joke(
        id = this.id,
        value = this.value,
        url = this.url ?: "",
        icon_url = this.iconUrl,
        // Si la API nos devuelve categories nulo (null), nosotros le pasamos una lista vacía
        // Así evitamos crashes por NullPointerException en nuestra UI.
        categories = this.categories ?: emptyList(),
        created_at = this.createdAt,
        updated_at = this.updatedAt
    )
}

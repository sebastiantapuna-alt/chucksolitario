package com.example.chucksolitario.data.chucknorris

import com.example.chucksolitario.models.Joke

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

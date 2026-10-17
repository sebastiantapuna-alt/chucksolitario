package com.example.chucksolitario.data.stoic
import com.example.chucksolitario.models.StoicQuote

fun StoicDto.toDomain(): StoicQuote {
    return StoicQuote(
        author = this.author ?: "",
        qoute = this.quote ?: ""
    )
}

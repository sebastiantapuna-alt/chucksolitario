package com.example.chucksolitario.models

data class KanyeQuoteResumen (
    val quote: String
) {
    fun aResumen(): KanyeQuoteResumen = KanyeQuoteResumen(quote = quote)
}
package com.example.chucksolitario.data

import com.example.chucksolitario.data.local.FavoritoEntity
import com.example.chucksolitario.models.KanyeQuoteResumen

/**
 * Un Mapeador (Mapper) es una función o clase que transforma un objeto DTO (el que viene de la API)
 * a un objeto de Dominio (nuestro Joke.kt que usamos en la app).
 * 
 * ¿Por qué hacemos esto?
 * Porque a veces las APIs devuelven valores nulos o nombres de variables raros. 
 * Con el mapeador "limpiamos" los datos antes de que lleguen a nuestra interfaz de usuario.
 */

fun FavoritoEntity.aResumen(): KanyeQuoteResumen =
    KanyeQuoteResumen(quote = quote)
fun KanyeQuoteResumen.aEntidad(): FavoritoEntity =
    FavoritoEntity(id = quote.hashCode(), quote = quote)


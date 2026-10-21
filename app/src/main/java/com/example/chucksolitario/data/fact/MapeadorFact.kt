package com.example.chucksolitario.data.fact

import com.example.chucksolitario.data.local.FactCacheEntity
import com.example.chucksolitario.data.local.FavoritoEntity
import com.example.chucksolitario.models.Fact

/**
 * Convierte un FactDto (de la API) a nuestro modelo de dominio (Fact).
 */
fun FactDto.toDomain(): Fact {
    return Fact(
        id = this.id ?: "",
        text = this.text ?: "Dato curioso no disponible",
        source = this.source ?: "Desconocido"
    )
}

/**
 * Convierte una entidad de Room (FavoritoEntity) a nuestro modelo de dominio (Fact).
 */
fun FavoritoEntity.toDomain(): Fact {
    return Fact(
        id = id,
        text = text,
        source = source
    )
}

/**
 * Convierte una entidad de la caché (FactCacheEntity) a nuestro modelo de dominio (Fact).
 */
fun FactCacheEntity.toDomain(): Fact {
    return Fact(
        id = id,
        text = text,
        source = source
    )
}

/**
 * Convierte nuestro modelo de dominio (Fact) a una entidad de la caché (FactCacheEntity).
 */
fun Fact.toCacheEntity(): FactCacheEntity {
    return FactCacheEntity(
        id = id,
        text = text,
        source = source
    )
}

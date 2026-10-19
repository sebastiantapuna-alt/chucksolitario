package com.example.chucksolitario.data

import com.example.chucksolitario.models.Fact

/**
 * Transforma un FactDto a un objeto limpio de Dominio (Fact).
 */
fun FactDto.toDomain(): Fact {
    return Fact(
        id = this.id ?: "",
        text = this.text ?: "Dato curioso no disponible",
        source = this.source ?: "Desconocido"
    )
}

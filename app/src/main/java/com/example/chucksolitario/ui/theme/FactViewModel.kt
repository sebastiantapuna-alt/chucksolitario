package com.example.chucksolitario.ui.theme

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.chucksolitario.data.FavoritosRepository
import com.example.chucksolitario.data.fact.FactRepository
import com.example.chucksolitario.data.local.AppDatabase
import com.example.chucksolitario.models.Fact
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class FactUiState(
    val fact: Fact? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val favoritosIds: Set<String> = emptySet()
)

private data class EstadoRed(
    val cargando: Boolean = true,
    val error: String? = null
)

class FactViewModel(application: Application) : AndroidViewModel(application) {

    private val baseDeDatos = AppDatabase.obtener(application)
    private val repository = FactRepository(baseDeDatos.factCacheDao())
    private val favoritosRepository = FavoritosRepository(baseDeDatos.favoritoDao())

    private val _estadoRed = MutableStateFlow(EstadoRed())

    // Combina la lista guardada en Room (1), el estado de red (2) y los IDs favoritos de Room (3)
    val uiState: StateFlow<FactUiState> = combine(
        repository.observarFacts(),
        _estadoRed,
        favoritosRepository.observarIds()
    ) { facts, red, favoritosIds ->
        FactUiState(
            fact = facts.lastOrNull(),
            isLoading = red.cargando,
            error = red.error,
            favoritosIds = favoritosIds
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = FactUiState(isLoading = true)
    )

    init {
        viewModelScope.launch {
            if (repository.hayFactsGuardados()) {
                // Ya hay datos guardados en Room: se muestran al instante sin esperar a la API
                _estadoRed.update { it.copy(cargando = false) }
            } else {
                // Primera vez (caché vacía): descargamos de la red
                fetchRandomFact()
            }
        }
    }

    fun fetchRandomFact() {
        _estadoRed.update { it.copy(cargando = true, error = null) }
        viewModelScope.launch {
            try {
                repository.cargarFactAleatorio()
                _estadoRed.update { it.copy(cargando = false) }
            } catch (e: Exception) {
                _estadoRed.update {
                    it.copy(cargando = false, error = "Error al obtener dato: ${e.localizedMessage}")
                }
            }
        }
    }

    fun alternarFavorito(fact: Fact) {
        val esFavorito = fact.id in uiState.value.favoritosIds
        viewModelScope.launch {
            if (esFavorito) {
                favoritosRepository.quitar(fact.id)
            } else {
                favoritosRepository.agregar(fact)
            }
        }
    }
}

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

class FactViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = FactRepository()
    private val favoritosRepository = FavoritosRepository(
        AppDatabase.obtener(application).favoritoDao()
    )

    // Estado interno que proviene de la API
    private val _estadoApi = MutableStateFlow(FactUiState())

    // Estado expuesto a la pantalla = Estado de la API + IDs favoritos emitidos por Room
    val uiState: StateFlow<FactUiState> = combine(
        _estadoApi,
        favoritosRepository.observarIds()
    ) { estadoApi, ids ->
        estadoApi.copy(favoritosIds = ids)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = FactUiState(isLoading = true)
    )

    init {
        fetchRandomFact()
    }

    fun fetchRandomFact() {
        _estadoApi.update { it.copy(isLoading = true, error = null) }
        viewModelScope.launch {
            try {
                val fact = repository.obtenerDatoAleatorio()
                _estadoApi.update { it.copy(fact = fact, isLoading = false) }
            } catch (e: Exception) {
                _estadoApi.update {
                    it.copy(isLoading = false, error = "Error al obtener dato: ${e.localizedMessage}")
                }
            }
        }
    }

    /**
     * Si el dato ya es favorito, lo elimina de Room; si no lo es, lo guarda.
     */
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

package com.example.chucksolitario.ui.theme

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
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

data class FactDetailUiState(
    val fact: Fact? = null,
    val isLoading: Boolean = true,
    val error: String? = null,
    val esFavorito: Boolean = false
)

class FactDetailViewModel(
    application: Application,
    savedStateHandle: SavedStateHandle
) : AndroidViewModel(application) {

    private val repository = FactRepository()
    private val favoritosRepository = FavoritosRepository(
        AppDatabase.obtener(application).favoritoDao()
    )

    private val factId: String? = savedStateHandle.get<String>(ARG_FACT_ID)

    private val _estadoApi = MutableStateFlow(FactDetailUiState())

    val uiState: StateFlow<FactDetailUiState> = combine(
        _estadoApi,
        favoritosRepository.observarIds()
    ) { estadoApi, ids ->
        val id = estadoApi.fact?.id
        estadoApi.copy(esFavorito = id != null && id in ids)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = FactDetailUiState()
    )

    init {
        cargar()
    }

    fun cargar() {
        _estadoApi.update { it.copy(isLoading = true, error = null) }
        viewModelScope.launch {
            try {
                val fact = repository.obtenerDatoAleatorio()
                _estadoApi.update { it.copy(fact = fact, isLoading = false) }
            } catch (e: Exception) {
                _estadoApi.update {
                    it.copy(isLoading = false, error = "Error al cargar detalle: ${e.localizedMessage}")
                }
            }
        }
    }

    fun alternarFavorito() {
        val estado = uiState.value
        val fact = estado.fact ?: return
        viewModelScope.launch {
            if (estado.esFavorito) {
                favoritosRepository.quitar(fact.id)
            } else {
                favoritosRepository.agregar(fact)
            }
        }
    }

    companion object {
        const val ARG_FACT_ID = "factId"
    }
}

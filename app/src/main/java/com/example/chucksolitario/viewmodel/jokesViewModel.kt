package com.example.chucksolitario.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chucksolitario.data.ChuckRepository
import com.example.chucksolitario.model.Joke
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class JokesDetailUiState(
    val joke: Joke? = null,
    val cargando: Boolean = true,
    val error: String? = null
)

class JokesViewModel(
    savedStateHandle: SavedStateHandle = SavedStateHandle(),
    private val repository: ChuckRepository = ChuckRepository()
) : ViewModel() {

    private val categoriaInicial: String? = savedStateHandle.get<String>(ARG_NOMBRE)

    private val _uiState = MutableStateFlow(JokesDetailUiState())
    val uiState: StateFlow<JokesDetailUiState> = _uiState.asStateFlow()

    init {
        cargarChiste(categoriaInicial ?: "random")
    }

    fun cargarChiste(categoria: String? = "random") {
        _uiState.update { it.copy(cargando = true, error = null) }

        val categoriaParam = if (categoria == "random") null else categoria

        viewModelScope.launch {
            repository.obtenerChiste(categoriaParam).fold(
                onSuccess = { joke ->
                    _uiState.update { it.copy(joke = joke, cargando = false) }
                },
                onFailure = { error ->
                    _uiState.update {
                        it.copy(
                            cargando = false,
                            error = "Ocurrió un problema al cargar el chiste: ${error.message}"
                        )
                    }
                }
            )
        }
    }

    companion object {
        const val ARG_NOMBRE = "categoria"
    }
}

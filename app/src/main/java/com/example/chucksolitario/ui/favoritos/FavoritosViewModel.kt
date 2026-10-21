package com.example.chucksolitario.ui.favoritos

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.chucksolitario.data.FavoritosRepository
import com.example.chucksolitario.data.local.AppDatabase
import com.example.chucksolitario.models.Fact
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class FavoritosUiState(
    val favoritos: List<Fact> = emptyList(),
    val cargando: Boolean = true
)

class FavoritosViewModel(application: Application) : AndroidViewModel(application) {

    private val favoritosRepository = FavoritosRepository(
        AppDatabase.obtener(application).favoritoDao()
    )

    val uiState: StateFlow<FavoritosUiState> = favoritosRepository.observarFavoritos()
        .map { lista -> FavoritosUiState(favoritos = lista, cargando = false) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = FavoritosUiState()
        )

    fun quitar(fact: Fact) {
        viewModelScope.launch {
            favoritosRepository.quitar(fact.id)
        }
    }
}

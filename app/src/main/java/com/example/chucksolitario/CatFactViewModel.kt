package com.example.chucksolitario

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chucksolitario.data.CatFactRepository
import com.example.chucksolitario.models.CatFact
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel para la pantalla de Cat Facts (Curiosidades sobre gatos)
 */
class CatFactViewModel : ViewModel() {

    private val repository = CatFactRepository()

    private val _catFactState = MutableStateFlow<CatFact?>(null)
    val catFactState: StateFlow<CatFact?> = _catFactState.asStateFlow()

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState.asStateFlow()

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        fetchRandomCatFact()
    }

    fun fetchRandomCatFact() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorState.value = null

            try {
                val catFact = repository.obtenerDatoGatoAleatorio()
                _catFactState.value = catFact
            } catch (e: Exception) {
                _errorState.value = "Error al obtener dato sobre gatos: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}

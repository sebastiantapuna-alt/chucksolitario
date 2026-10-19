package com.example.chucksolitario.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chucksolitario.data.FactRepository
import com.example.chucksolitario.models.Fact
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FactViewModel : ViewModel() {

    private val repository = FactRepository()

    private val _factState = MutableStateFlow<Fact?>(null)
    val factState: StateFlow<Fact?> = _factState.asStateFlow()

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState.asStateFlow()

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        fetchRandomFact()
    }

    fun fetchRandomFact() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorState.value = null

            try {
                val fact = repository.obtenerDatoAleatorio()
                _factState.value = fact
            } catch (e: Exception) {
                _errorState.value = "Error al obtener dato: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}

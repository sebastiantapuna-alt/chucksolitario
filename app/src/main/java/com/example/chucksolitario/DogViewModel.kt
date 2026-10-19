package com.example.chucksolitario

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chucksolitario.data.DogRepository
import com.example.chucksolitario.models.DogImage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel para la pantalla de Dog CEO API (Imágenes aleatorias de perros)
 */
class DogViewModel : ViewModel() {

    private val repository = DogRepository()

    private val _dogImageState = MutableStateFlow<DogImage?>(null)
    val dogImageState: StateFlow<DogImage?> = _dogImageState.asStateFlow()

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState.asStateFlow()

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        fetchRandomDogImage()
    }

    fun fetchRandomDogImage() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorState.value = null

            try {
                val dogImage = repository.obtenerImagenPerroAleatoria()
                _dogImageState.value = dogImage
            } catch (e: Exception) {
                _errorState.value = "Error al obtener imagen de perro: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}

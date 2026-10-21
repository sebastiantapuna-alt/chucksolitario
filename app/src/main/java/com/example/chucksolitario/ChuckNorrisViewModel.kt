package com.example.chucksolitario

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chucksolitario.data.chucknorris.ChuckNorrisRepository
import com.example.chucksolitario.models.Joke
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChuckNorrisViewModel : ViewModel() {

    private val repository = ChuckNorrisRepository()

    private val _jokeState = MutableStateFlow<Joke?>(null)
    val jokeState: StateFlow<Joke?> = _jokeState.asStateFlow()

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState.asStateFlow()

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        fetchRandomJoke()
    }

    fun fetchRandomJoke() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorState.value = null
            
            try {
                val joke = repository.obtenerChisteAleatorio()
                _jokeState.value = joke
            } catch (e: Exception) {
                _errorState.value = "Hubo un error: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}

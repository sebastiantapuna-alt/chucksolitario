package com.example.chucksolitario.ui.theme


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chucksolitario.data.TimeRepository
import com.example.chucksolitario.models.TimeInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TimeViewModel : ViewModel() {

    private val repository = TimeRepository()

    // Estado del tiempo (el "Joke" de esta API)
    private val _timeState = MutableStateFlow<TimeInfo?>(null)
    val timeState: StateFlow<TimeInfo?> = _timeState.asStateFlow()

    // Estado de error
    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState.asStateFlow()

    // Estado de carga
    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        // Buscamos la hora de La Paz al abrir la pantalla
        fetchTime("America", "La_Paz")
    }

    fun fetchTime(area: String, location: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorState.value = null

            try {
                val timeInfo = repository.obtenerHoraActual(area, location)
                _timeState.value = timeInfo
            } catch (e: Exception) {
                _errorState.value = "Error al obtener la hora: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
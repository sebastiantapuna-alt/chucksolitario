package com.example.chucksolitario.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chucksolitario.data.holiday.HolidayRepository
import com.example.chucksolitario.models.Holiday
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HolidayViewModel : ViewModel() {

    private val repository = HolidayRepository()

    private val _holidayState = MutableStateFlow<List<Holiday>>(emptyList())
    val holidayState: StateFlow<List<Holiday>> = _holidayState.asStateFlow()

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState.asStateFlow()

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        fetchHolidays("2026", "BO")
    }

    fun fetchHolidays(year: String, countryCode: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorState.value = null

            try {
                val listaFeriados = repository.obtenerListaFeriados(year, countryCode)
                _holidayState.value = listaFeriados
            } catch (e: Exception) {
                _errorState.value = "Error al obtener feriados: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}

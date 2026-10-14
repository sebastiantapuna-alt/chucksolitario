package com.example.chucksolitario

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chucksolitario.data.ChuckNorrisRepository
import com.example.chucksolitario.models.Joke
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel: Es el cerebro de la pantalla. 
 * Conecta el Repositorio (datos) con la Vista (Compose).
 * Su ventaja es que los datos no se pierden al girar la pantalla del celular.
 */
class ChuckNorrisViewModel : ViewModel() {

    // Instanciamos el repositorio que creamos antes
    private val repository = ChuckNorrisRepository()

    // _jokeState es mutable, aquí guardamos el chiste en secreto dentro del ViewModel.
    // Inicia en "null" porque al principio no tenemos chiste.
    private val _jokeState = MutableStateFlow<Joke?>(null)
    
    // jokeState es público y NO mutable (solo de lectura). 
    // Es el que la UI va a observar para actualizarse sola.
    val jokeState: StateFlow<Joke?> = _jokeState.asStateFlow()

    // Guardamos si hay un error
    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState.asStateFlow()

    // Guardamos si está cargando (para mostrar un spinner en el futuro si quieres)
    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        // Ejecutamos esto apenas se crea el ViewModel
        // Para que al abrir la app ya haya un chiste cargando.
        fetchRandomJoke()
    }

    /**
     * Esta función llama al Repositorio para pedir un chiste nuevo.
     */
    fun fetchRandomJoke() {
        // viewModelScope lanza una "Corrutina" (un hilo secundario).
        // Las llamadas a internet no se pueden hacer en el hilo principal o la app se traba.
        viewModelScope.launch {
            _isLoading.value = true // Avisamos a la UI que estamos cargando
            _errorState.value = null // Limpiamos errores pasados
            
            try {
                // repository va, llama a retrofit, mapea el DTO y nos da el Joke puro.
                val joke = repository.obtenerChisteAleatorio()
                // Metemos el chiste en nuestro estado. La pantalla (Compose) se actualizará automáticamente.
                _jokeState.value = joke
            } catch (e: Exception) {
                // Si alguien apagó el WiFi o se cae el server de Chuck Norris
                _errorState.value = "Hubo un error: ${e.localizedMessage}"
            } finally {
                // Pase lo que pase (éxito o error), terminamos de cargar.
                _isLoading.value = false
            }
        }
    }
}
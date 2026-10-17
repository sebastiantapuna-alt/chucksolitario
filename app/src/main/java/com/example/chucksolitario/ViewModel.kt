package com.example.chucksolitario

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chucksolitario.data.ChuckNorrisRepository
import com.example.chucksolitario.data.ai.AiAnswer
import com.example.chucksolitario.data.ai.AiRepository
import com.example.chucksolitario.data.kanye.KanyeQuote
import com.example.chucksolitario.data.kanye.KanyeQuoteRepository
import com.example.chucksolitario.data.stoic.StoicRepository
import com.example.chucksolitario.models.StoicQuote
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
class ViewModel : ViewModel() {

    // Instanciamos los repositorios de las API's
    private val repository = ChuckNorrisRepository()
    private val stoicRepository = StoicRepository()
    private val kanyeRepository = KanyeQuoteRepository()
    private val geminiRepository = AiRepository()

    // _valueState es para la pantalla Estoica
    private val _valueState = MutableStateFlow<StoicQuote?>(null)
    val valueState: StateFlow<StoicQuote?> = _valueState.asStateFlow()

    // Creamos un estado exclusivo para la pantalla de Chuck Norris
    private val _jokeState = MutableStateFlow<Joke?>(null)
    val jokeState: StateFlow<Joke?> = _jokeState.asStateFlow()

    // Creamos un estado exclusivo para la pantalla de Kanye West
    private val _kanyeState = MutableStateFlow<KanyeQuote?>(null)
    val kanyeState: StateFlow<KanyeQuote?> = _kanyeState.asStateFlow()

    // Creamos un estado exclusivo para la pantalla de Gemini
    private val _geminiState = MutableStateFlow<AiAnswer?>(null)
    val geminiState: StateFlow<AiAnswer?> = _geminiState.asStateFlow()





    // Guardamos si hay un error
    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState.asStateFlow()

    // Guardamos si está cargando
    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        // Al iniciar
    }

    fun fetchStoicQuote(){
        viewModelScope.launch {
            _isLoading.value = true
            _errorState.value = null

            try {
                val quote = stoicRepository.obtenerCitaAleatoria()
                _valueState.value = quote
            } catch (e: Exception) {
                _errorState.value = "Hubo un error: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun fetchKanyeQuote(){
        viewModelScope.launch {
            _isLoading.value = true
            _errorState.value = null

            try {
                val quote = kanyeRepository.obtenerCItaKanyeAleatoria()
                _kanyeState.value = quote
            } catch (e: Exception) {
                _errorState.value = "Hubo un error: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    /**
     * Esta función llama al Repositorio para pedir un chiste nuevo de Chuck Norris.
     */
    fun fetchRandomJoke() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorState.value = null
            
            try {
                val joke = repository.obtenerChisteAleatorio()
                // Actualizamos el estado de Chuck Norris!
                _jokeState.value = joke
            } catch (e: Exception) {
                _errorState.value = "Hubo un error: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun fetchGeminiAnswer(prompt: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorState.value = null
            _geminiState.value = null
            try {
                val geminiAnswer = geminiRepository.obtenerRespuestaGemini(prompt)
                _geminiState.value = geminiAnswer
            } catch (e: retrofit2.HttpException) {
                when (e.code()) {
                    429 -> _errorState.value = "Excediste el límite de peticiones de la API gratuita de Gemini (429). Espera 15-30 segundos e intenta de nuevo."
                    503 -> _errorState.value = "El servicio de Gemini está ocupado temporalmente (503). Por favor reintenta en unos segundos."
                    else -> _errorState.value = "Hubo un error: ${e.localizedMessage}"
                }
            } catch (e: Exception) {
                _errorState.value = "Hubo un error: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
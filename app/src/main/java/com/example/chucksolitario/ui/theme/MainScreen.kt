package com.example.chucksolitario.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.chucksolitario.Greeting
import com.example.chucksolitario.R
import com.example.chucksolitario.ViewModel

// Paleta base
val backgroundColor = Color(0xFF121212)
val surfaceColor = Color(0xFF1E1E1E)
val textColor = Color(0xFFF5F5F5)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChuckNorrisApp(
    viewModel: ViewModel = viewModel()
) {
    // Estado para saber qué tab está seleccionado (0 = Chuck, 1 = Stoic, 2 = Poke)
    var selectedTab by remember { mutableStateOf(0) }

    // Colores dinámicos dependiendo del tab
    val primaryColor = when (selectedTab) {
        0 -> Color(0xFFFF5722) // Naranja intenso para Chuck
        1 -> Color(0xFF6200EA) // Púrpura profundo para Stoic
        else -> Color(0xFFE91E63) // Rosa para la 3era pantalla (Poke)
    }

    val appBarTitle = when (selectedTab) {
        0 -> "Gemini Api"
        1 -> "Stoic Quotes"
        else -> "Kanye API"
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = backgroundColor,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = appBarTitle,
                        fontWeight = FontWeight.Black,
                        fontSize = 26.sp,
                        color = primaryColor,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = surfaceColor
                ),
                modifier = Modifier.shadow(8.dp)
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = surfaceColor,
                contentColor = textColor
            ) {
                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = { Icon(Icons.Default.Star, contentDescription = "Gemini") },
                    label = { Text("Gemini") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        selectedTextColor = primaryColor,
                        indicatorColor = primaryColor,
                        unselectedIconColor = Color.Gray,
                        unselectedTextColor = Color.Gray
                    )
                )
                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    icon = { Icon(Icons.Default.Face, contentDescription = "Stoic") },
                    label = { Text("Stoic") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        selectedTextColor = primaryColor,
                        indicatorColor = primaryColor,
                        unselectedIconColor = Color.Gray,
                        unselectedTextColor = Color.Gray
                    )
                )
                NavigationBarItem(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    icon = { Icon(Icons.Default.Info, contentDescription = "Kanye") },
                    label = { Text("Kanye") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        selectedTextColor = primaryColor,
                        indicatorColor = primaryColor,
                        unselectedIconColor = Color.Gray,
                        unselectedTextColor = Color.Gray
                    )
                )
            }
        }
    ) { innerPadding ->
        // Reutilizamos el mismo layout para las tres pantallas
        DynamicScreenContent(
            selectedTab = selectedTab,
            viewModel = viewModel,
            primaryColor = primaryColor,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun DynamicScreenContent(
    selectedTab: Int,
    viewModel: ViewModel,
    primaryColor: Color,
    modifier: Modifier = Modifier
) {
    // Observamos los estados de nuestro ViewModel
    val value by viewModel.valueState.collectAsState()
    val kanyeQuote by viewModel.kanyeState.collectAsState()
    val geminiAnswer by viewModel.geminiState.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.errorState.collectAsState()

    // Configuraciones específicas por tab
    val greetingName = when (selectedTab) {
        0 -> "GEMINI"
        1 -> "MARCO AURELIO"
        else -> "KANYE"
    }

    val imageRes = when (selectedTab) {
        0 -> R.drawable.gemini
        1 -> R.drawable.stoicportait
        else -> R.drawable.kanye
    }

    // Estado local para el campo de texto de Gemini
    var promptText by remember { mutableStateOf("") }

    // Al clickear el botón, ejecutamos el request específico
    val buttonAction: () -> Unit = {
        when (selectedTab) {
            // Le pasamos el texto escrito por el usuario
            0 -> viewModel.fetchGeminiAnswer(promptText) 
            1 -> viewModel.fetchStoicQuote()
            else -> viewModel.fetchKanyeQuote()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Greeting(
            name = greetingName,
            textColor = textColor
        )

        Image(
            painter = painterResource(id = imageRes),
            contentDescription = greetingName,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(160.dp) // Achicamos un poco la imagen para que quepa el input
                .clip(RoundedCornerShape(16.dp))
                .border(3.dp, primaryColor, RoundedCornerShape(16.dp))
                .shadow(12.dp, RoundedCornerShape(16.dp))
        )

        // Si estamos en la pestaña 0 (Gemini), mostramos un TextField
        if (selectedTab == 0) {
            TextField(
                value = promptText,
                onValueChange = { promptText = it },
                placeholder = { Text("Escribe tu pregunta para Gemini...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clip(RoundedCornerShape(12.dp)),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = surfaceColor,
                    unfocusedContainerColor = surfaceColor,
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        }

        // Qué texto mostramos en el cuadro central
        val textToShow = when {
            isLoading -> "Buscando información..."
            error != null -> error!!
            geminiAnswer != null && selectedTab == 0 -> geminiAnswer!!.answer
            value != null && selectedTab == 1 -> "\"${value!!.qoute}\" \n\n- ${value!!.author}"
            kanyeQuote != null && selectedTab == 2 -> kanyeQuote!!.quote
            else -> if (selectedTab == 0) "Escribe algo arriba y presiona enviar" else "Presiona Random para obtener data"
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(vertical = 8.dp)
                .background(surfaceColor, RoundedCornerShape(12.dp))
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = textToShow,
                    color = textColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = if (selectedTab == 0 && geminiAnswer != null) TextAlign.Start else TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Button(
            onClick = buttonAction,
            colors = ButtonDefaults.buttonColors(
                containerColor = primaryColor,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp),
            // Desactivamos el botón si la pestaña es Gemini y no han escrito nada
            enabled = !isLoading && (selectedTab != 0 || promptText.isNotBlank()),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .shadow(8.dp, RoundedCornerShape(12.dp))
        ) {
            Text(
                text = if (selectedTab == 0) "ENVIAR" else "RANDOM",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp
            )
        }
    }
}
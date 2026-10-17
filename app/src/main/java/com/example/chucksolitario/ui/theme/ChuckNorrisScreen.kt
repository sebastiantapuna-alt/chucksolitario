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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
        0 -> "Chuck Norris"
        1 -> "Stoic Quotes"
        else -> "Pokémon API"
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
                    icon = { Icon(Icons.Default.Star, contentDescription = "Chuck") },
                    label = { Text("Chuck") },
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
                    icon = { Icon(Icons.Default.Info, contentDescription = "Poke") },
                    label = { Text("Poke") },
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
    val joke by viewModel.jokeState.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.errorState.collectAsState()

    // Configuraciones específicas por tab
    val greetingName = when (selectedTab) {
        0 -> "CHUCK NORRIS"
        1 -> "MARCO AURELIO"
        else -> "PIKACHU"
    }

    val imageRes = when (selectedTab) {
        0 -> R.drawable.images
        1 -> R.drawable.stoicportait // Tu imagen estoica renombrada
        else -> R.drawable.images // (Puedes poner otra imagen aquí luego)
    }

    // Al clickear el botón, ejecutamos el request específico
    val buttonAction: () -> Unit = {
        when (selectedTab) {
            0 -> viewModel.fetchRandomJoke() // Ojo: Tendrás que ajustar en tu VM para que asigne a un StateFlow adecuado
            1 -> viewModel.fetchStoicQuote()
            else -> viewModel.fetchRandomJoke() 
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
                .size(240.dp)
                .clip(RoundedCornerShape(16.dp))
                .border(3.dp, primaryColor, RoundedCornerShape(16.dp))
                .shadow(12.dp, RoundedCornerShape(16.dp))
        )

        // Qué texto mostramos en el cuadro central
        val textToShow = when {
            isLoading -> "Buscando información..."
            error != null -> error!!
            value != null && selectedTab == 1 -> "\"${value!!.qoute}\" \n\n- ${value!!.author}"
            joke != null && selectedTab == 0 -> joke!!.value
            else -> "Presiona Random para obtener data"
        }

        Text(
            text = textToShow,
            color = textColor,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(surfaceColor, RoundedCornerShape(12.dp))
                .padding(24.dp)
        )

        Button(
            onClick = buttonAction,
            colors = ButtonDefaults.buttonColors(
                containerColor = primaryColor,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .shadow(8.dp, RoundedCornerShape(12.dp))
        ) {
            Text(
                text = "Random",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp
            )
        }
    }
}
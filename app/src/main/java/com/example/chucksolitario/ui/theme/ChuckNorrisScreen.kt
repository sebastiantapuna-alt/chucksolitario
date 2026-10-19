package com.example.chucksolitario.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.mutableIntStateOf
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
import coil.compose.SubcomposeAsyncImage
import com.example.chucksolitario.CatFactViewModel
import com.example.chucksolitario.ChuckNorrisViewModel
import com.example.chucksolitario.DogViewModel
import com.example.chucksolitario.Greeting
import com.example.chucksolitario.R

// Paleta de Colores Dark Mode
val backgroundColor = Color(0xFF121212)
val primaryColor = Color(0xFFFF5722) // Naranja de Acción
val secondaryColor = Color(0xFF03A9F4) // Azul secundario
val accentColor = Color(0xFF4CAF50) // Verde de acento
val surfaceColor = Color(0xFF1E1E1E)
val textColor = Color(0xFFF5F5F5)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChuckNorrisApp() {
    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = backgroundColor,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = when (selectedTab) {
                            0 -> "Chuck Norris API"
                            1 -> "Cat Facts API"
                            else -> "Dog CEO API"
                        },
                        fontWeight = FontWeight.Black,
                        fontSize = 24.sp,
                        color = when (selectedTab) {
                            0 -> primaryColor
                            1 -> secondaryColor
                            else -> accentColor
                        },
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
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
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Face,
                            contentDescription = "Chuck Norris",
                            tint = if (selectedTab == 0) primaryColor else Color.Gray
                        )
                    },
                    label = {
                        Text(
                            text = "Chuck Norris",
                            color = if (selectedTab == 0) primaryColor else Color.Gray,
                            fontWeight = if (selectedTab == 0) FontWeight.Bold else FontWeight.Normal
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = surfaceColor
                    )
                )
                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Cat Facts",
                            tint = if (selectedTab == 1) secondaryColor else Color.Gray
                        )
                    },
                    label = {
                        Text(
                            text = "Cat Facts",
                            color = if (selectedTab == 1) secondaryColor else Color.Gray,
                            fontWeight = if (selectedTab == 1) FontWeight.Bold else FontWeight.Normal
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = surfaceColor
                    )
                )
                NavigationBarItem(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Dog API",
                            tint = if (selectedTab == 2) accentColor else Color.Gray
                        )
                    },
                    label = {
                        Text(
                            text = "Dog API",
                            color = if (selectedTab == 2) accentColor else Color.Gray,
                            fontWeight = if (selectedTab == 2) FontWeight.Bold else FontWeight.Normal
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = surfaceColor
                    )
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (selectedTab) {
                0 -> ChuckNorrisTabContent()
                1 -> CatFactTabContent()
                2 -> DogTabContent()
            }
        }
    }
}

/**
 * Pestaña 1: API de Chuck Norris
 */
@Composable
fun ChuckNorrisTabContent(
    viewModel: ChuckNorrisViewModel = viewModel()
) {
    val joke by viewModel.jokeState.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.errorState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Greeting(
            name = "CHUCK NORRIS",
            textColor = textColor
        )

        Image(
            painter = painterResource(id = R.drawable.images),
            contentDescription = "Chuck Norris",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(16.dp))
                .border(3.dp, primaryColor, RoundedCornerShape(16.dp))
                .shadow(12.dp, RoundedCornerShape(16.dp))
        )

        val textToShow = when {
            isLoading -> "Buscando chiste con patada voladora..."
            error != null -> error!!
            joke != null -> joke!!.value
            else -> "Presiona Random para un chiste"
        }

        Text(
            text = textToShow,
            color = textColor,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(surfaceColor, RoundedCornerShape(12.dp))
                .padding(20.dp)
        )

        Button(
            onClick = { viewModel.fetchRandomJoke() },
            colors = ButtonDefaults.buttonColors(
                containerColor = primaryColor,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .shadow(8.dp, RoundedCornerShape(12.dp))
        ) {
            Text(
                text = "Random Joke",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
        }
    }
}

/**
 * Pestaña 2: API de Cat Facts (Datos curiosos de gatos)
 */
@Composable
fun CatFactTabContent(
    viewModel: CatFactViewModel = viewModel()
) {
    val catFact by viewModel.catFactState.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.errorState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Greeting(
            name = "CAT FACTS",
            textColor = secondaryColor
        )

        val textToShow = when {
            isLoading -> "Obteniendo dato curioso de gatos..."
            error != null -> error!!
            catFact != null -> "🐱 \"${catFact?.fact}\"\n\nLongitud: ${catFact?.length} caracteres"
            else -> "Presiona el botón para un dato gatuno"
        }

        Text(
            text = textToShow,
            color = textColor,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(surfaceColor, RoundedCornerShape(12.dp))
                .border(1.dp, secondaryColor.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
                .padding(20.dp)
        )

        Button(
            onClick = { viewModel.fetchRandomCatFact() },
            colors = ButtonDefaults.buttonColors(
                containerColor = secondaryColor,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .shadow(8.dp, RoundedCornerShape(12.dp))
        ) {
            Text(
                text = "Random Cat Fact",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
        }
    }
}

/**
 * Pestaña 3: API de Dog CEO (Imágenes aleatorias de perros)
 */
@Composable
fun DogTabContent(
    viewModel: DogViewModel = viewModel()
) {
    val dogImage by viewModel.dogImageState.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.errorState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Greeting(
            name = "DOG API",
            textColor = accentColor
        )

        Box(
            modifier = Modifier
                .size(240.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(surfaceColor)
                .border(3.dp, accentColor, RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            val currentDog = dogImage
            if (isLoading) {
                CircularProgressIndicator(color = accentColor)
            } else if (currentDog != null && currentDog.imageUrl.isNotEmpty()) {
                SubcomposeAsyncImage(
                    model = currentDog.imageUrl,
                    contentDescription = "Perro aleatorio",
                    contentScale = ContentScale.Crop,
                    loading = {
                        CircularProgressIndicator(color = accentColor)
                    },
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Text(
                    text = "🐶\nPresiona el botón para cargar una imagen de perro",
                    color = textColor,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        if (error != null) {
            Text(
                text = error!!,
                color = Color.Red,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
        }

        Button(
            onClick = { viewModel.fetchRandomDogImage() },
            colors = ButtonDefaults.buttonColors(
                containerColor = accentColor,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .shadow(8.dp, RoundedCornerShape(12.dp))
        ) {
            Text(
                text = "Random Dog Image",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
        }
    }
}

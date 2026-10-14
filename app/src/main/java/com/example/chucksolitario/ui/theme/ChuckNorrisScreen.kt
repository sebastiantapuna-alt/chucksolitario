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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
import com.example.chucksolitario.Greeting
import com.example.chucksolitario.R
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.chucksolitario.ChuckNorrisViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator

// Paleta Cool (Dark Mode + Naranja de Acción)
val backgroundColor = Color(0xFF121212)
val primaryColor = Color(0xFFFF5722) // Naranja intenso
val surfaceColor = Color(0xFF1E1E1E)
val textColor = Color(0xFFF5F5F5)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChuckNorrisApp(
    // Inyectamos el ViewModel automáticamente usando la función viewModel()
    viewModel: ChuckNorrisViewModel = viewModel()
) {
    // Observamos los Estados del ViewModel. Al cambiar, la UI se redibuja sola (recomposición).
    val joke by viewModel.jokeState.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.errorState.collectAsState()

Scaffold(
modifier = Modifier.fillMaxSize(),
containerColor = backgroundColor,
topBar = {
    TopAppBar(
        title = {
            Text(
                text = "Chuck Norris Jokes",
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
}
) { innerPadding ->
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
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
                .size(240.dp)
                .clip(RoundedCornerShape(16.dp))
                .border(3.dp, primaryColor, RoundedCornerShape(16.dp))
                .shadow(12.dp, RoundedCornerShape(16.dp))
        )

        // Dependiendo del estado, mostramos Loading, Error o el Chiste
        val textToShow = when {
            isLoading -> "Buscando chiste con patada voladora..."
            error != null -> error!! // Mostramos el error si ocurrió alguno
            joke != null -> joke!!.value // Extraemos el texto del chiste del objeto Joke
            else -> "Presiona Random para un chiste"
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
            // Cuando hacen click, llamamos a la función del ViewModel que trae otro chiste!
            onClick = { viewModel.fetchRandomJoke() },
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
}
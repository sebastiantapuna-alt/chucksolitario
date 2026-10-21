package com.example.chucksolitario.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FactScreen(
    viewModel: FactViewModel = viewModel(),
    onVerFavoritos: () -> Unit = {},
    onFactClick: (String) -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color(0xFF1E1E1E), RoundedCornerShape(12.dp))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Dato Inútil Aleatorio",
                color = Color(0xFFFF5722),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            IconButton(onClick = onVerFavoritos) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Ver favoritos",
                    tint = Color(0xFFFF5722)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        when {
            uiState.isLoading -> {
                CircularProgressIndicator(color = Color(0xFFFF5722))
            }
            uiState.error != null -> {
                Text(text = uiState.error!!, color = Color.Red, textAlign = TextAlign.Center)
            }
            uiState.fact != null -> {
                val fact = uiState.fact!!
                FactCard(
                    fact = fact,
                    esFavorito = fact.id in uiState.favoritosIds,
                    onClick = { onFactClick(fact.id) },
                    onFavoritoClick = { viewModel.alternarFavorito(fact) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.fetchRandomFact() },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5722))
        ) {
            Text("Otro Dato Inútil", color = Color.White)
        }
    }
}

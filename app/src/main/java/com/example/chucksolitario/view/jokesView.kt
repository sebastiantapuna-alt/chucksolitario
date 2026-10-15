package com.example.chucksolitario.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chucksolitario.ui.theme.ChucksolitarioTheme
import com.example.chucksolitario.viewmodel.JokesViewModel

@Composable
fun ChuckMain(
    modifier: Modifier = Modifier,
    jokesViewModel: JokesViewModel = remember { JokesViewModel() }
) {
    val uiState by jokesViewModel.uiState.collectAsState()

    val categorias = listOf(
        "animal", "career", "celebrity", "dev", "explicit", "fashion",
        "food", "history", "money", "movie", "music", "political",
        "religion", "science", "sport", "travel", "random"
    )

    var categoriaSeleccionada by remember { mutableStateOf("random") }
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF0F2027),
                        Color(0xFF203A43),
                        Color(0xFF2C5364)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.88f)
                .fillMaxHeight(0.45f),
            shape = RoundedCornerShape(28.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF1E293B),
                contentColor = Color.White
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Botón tipo Selector de Categoría con Lista Desplegable
                Box {
                    Surface(
                        onClick = { expanded = true },
                        shape = RoundedCornerShape(50),
                        color = Color(0xFF334155)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            Text(
                                text = "Categoría: $categoriaSeleccionada ▼",
                                color = Color(0xFF38BDF8),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier
                            .background(Color(0xFF334155))
                            .width(180.dp)//ancho
                            .heightIn(max = 220.dp)//alto
                    ) {
                        categorias.forEach { categoria ->
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = categoria,
                                        color = if (categoria == categoriaSeleccionada) Color(0xFF38BDF8) else Color.White,
                                        fontWeight = if (categoria == categoriaSeleccionada) FontWeight.Bold else FontWeight.Normal,
                                        fontSize = 14.sp
                                    )
                                },
                                onClick = {
                                    categoriaSeleccionada = categoria
                                    expanded = false
                                    jokesViewModel.cargarChiste(categoria)
                                }
                            )
                        }
                    }
                }

                if (uiState.cargando) {
                    CircularProgressIndicator(
                        color = Color(0xFF38BDF8),
                        modifier = Modifier.padding(16.dp)
                    )
                } else if (uiState.error != null) {
                    Text(
                        text = uiState.error ?: "",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFFEF4444),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )
                } else {
                    Text(
                        text = uiState.joke?.valor ?: "Haz clic abajo para obtener un chiste",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFFF8FAFC),
                        textAlign = TextAlign.Center,
                        lineHeight = 22.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )
                }

                Button(
                    onClick = { jokesViewModel.cargarChiste(categoriaSeleccionada) },
                    enabled = !uiState.cargando,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF0284C7),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .height(48.dp)
                ) {
                    Text(
                        text = "Siguiente Chiste",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ChuckMainPreview() {
    ChucksolitarioTheme {
        ChuckMain()
    }
}

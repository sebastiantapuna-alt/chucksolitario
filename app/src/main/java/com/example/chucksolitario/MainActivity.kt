package com.example.chucksolitario

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chucksolitario.ui.theme.ChucksolitarioTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Forzamos el dark theme y deshabilitamos colores dinámicos para que la paleta destaque
            ChucksolitarioTheme(darkTheme = true, dynamicColor = false) {
                
                // Paleta Cool (Dark Mode + Naranja de Acción)
                val backgroundColor = Color(0xFF121212)
                val primaryColor = Color(0xFFFF5722) // Naranja intenso
                val surfaceColor = Color(0xFF1E1E1E)
                val textColor = Color(0xFFF5F5F5)

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
                        
                        Text(
                            text = "Chuck Norris Jokes",
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
                            onClick = { print("Holi") },
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
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, textColor: Color = Color.White) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
        fontSize = 28.sp,
        fontWeight = FontWeight.ExtraBold,
        textAlign = TextAlign.Center,
        color = textColor
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ChucksolitarioTheme {
        Greeting("CHUCK NORRIS")
    }
}

package com.example.chucksolitario

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.chucksolitario.ui.theme.ChuckNorrisApp
import com.example.chucksolitario.ui.theme.ChucksolitarioTheme
import com.example.chucksolitario.ui.theme.FactScreen
import com.example.chucksolitario.ui.theme.HolidayScreen
import com.example.chucksolitario.ui.theme.TimeScreen

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChucksolitarioTheme(darkTheme = true, dynamicColor = false) {
                // Columna principal con la vista de las 4 APIs
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    // 1. Chuck Norris
                    Box(
                        modifier = Modifier.weight(1f)
                    ) {
                        ChuckNorrisApp()
                    }

                    // 2. Feriados
                    Box(
                        modifier = Modifier.weight(1f)
                    ) {
                        HolidayScreen()
                    }

                    // 3. Dato Inútil Aleatorio
                    FactScreen()

                    // 4. El Tiempo
                    TimeScreen()
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

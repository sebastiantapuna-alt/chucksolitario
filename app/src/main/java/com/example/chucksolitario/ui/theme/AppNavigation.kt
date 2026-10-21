package com.example.chucksolitario.ui.theme

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chucksolitario.ui.favoritos.FavoritosScreen

private const val RUTA_PRINCIPAL = "principal"
private const val RUTA_DETALLE = "detalle"
private const val RUTA_FAVORITOS = "favoritos"

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = RUTA_PRINCIPAL
    ) {
        composable(RUTA_PRINCIPAL) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    ChuckNorrisApp()
                }
                Box(modifier = Modifier.weight(1f)) {
                    HolidayScreen()
                }
                FactScreen(
                    onVerFavoritos = { navController.navigate(RUTA_FAVORITOS) },
                    onFactClick = { id -> navController.navigate("$RUTA_DETALLE/$id") }
                )
                TimeScreen()
            }
        }
        composable("$RUTA_DETALLE/{factId}") {
            FactDetailScreen(
                onVolver = { navController.popBackStack() }
            )
        }
        composable(RUTA_FAVORITOS) {
            FavoritosScreen(
                onVolver = { navController.popBackStack() },
                onFactClick = { id -> navController.navigate("$RUTA_DETALLE/$id") }
            )
        }
    }
}

package com.example.group00.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.circles.CircleScreen
import com.example.group00.Menu
import com.example.highintensive.HighIntensiveScreen
import com.example.primenumbers.PrimeScreen
import com.example.thermometer.ThermoScreen

@Composable
fun Nav() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Menu") {
        composable(route = "Menu") {
            Menu(navController)
        }

        composable(route = "CircleScreen") {
            CircleScreen(navController)
        }

        composable(route = "PrimeScreen") {
            PrimeScreen(navController)
        }

        composable(route = "ThermoScreen") {
            ThermoScreen(navController)
        }

        composable(route = "HighIntensive") {
            HighIntensiveScreen(navController)
        }
    }
}
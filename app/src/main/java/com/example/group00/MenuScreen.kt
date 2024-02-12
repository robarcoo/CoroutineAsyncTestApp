package com.example.group00

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ui.components.NavigationButton


@Composable
fun Menu(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NavigationButton(
            buttonText = "Circles",
            navController,
            "CircleScreen"
        )
        NavigationButton(
            buttonText = "Prime Numbers",
            navController,
            "PrimeScreen"
        )
        NavigationButton(
            buttonText = "Thermometer",
            navController,
            "ThermoScreen"
        )
        NavigationButton(
            buttonText = "High Intensive Calculator",
            navController,
            "HighIntensive"
        )
    }

}

@Preview(showBackground = true)
@Composable
fun MenuPreview() {
    Menu(navController = rememberNavController())
}
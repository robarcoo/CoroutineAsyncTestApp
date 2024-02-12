package com.example.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.logger.Logger

@Composable
fun NavigationButton(buttonText: String, navController: NavController, destination: String) {
    Button(
        onClick = {
            Logger.info("Переход на экран $destination")
            navController.navigate(destination)
        },
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .padding(6.dp)
    ) { Text(text = buttonText, fontSize = 20.sp) }
}

@Composable
fun BackToMenuButton(navController: NavController) {
    Button(onClick = {
        Logger.info("Возвращение в меню")
        navController.navigate("Menu")
    }) { Text(text = "Назад") }
}
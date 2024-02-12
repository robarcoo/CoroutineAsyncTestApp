package com.example.primenumbers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.buildAnnotatedString
import androidx.navigation.NavController
import com.example.data.PrintNumbers
import com.example.data.isPrime
import com.example.ui.components.BackToMenuButton
import com.example.ui.components.TextComponent
import com.example.ui.components.fieldComponent
import kotlinx.coroutines.launch
import com.example.logger.Logger
import kotlin.text.Typography.bullet

@Composable
fun PrimeScreen(navController: NavController) {
    var state by remember { mutableStateOf(true) }
    var number by remember { mutableStateOf("0") }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BackToMenuButton(navController = navController)

        Row {
            RadioButton(
                selected = state,
                onClick = {
                    state = true
                }
            )
            TextComponent(text = "Higher")
        }
        Row {
            RadioButton(
                selected = !state,
                onClick = {
                    state = false
                }
            )
            TextComponent(text = "Lower")
        }

        number = fieldComponent(
            value = number,
            text = "Введите число",
            stringPattern = "\\d*"
        )

        Print(number, state)
    }
}

@Composable
fun Print(number: String, state: Boolean) {
    val coroutineScope = rememberCoroutineScope()
    val correctNumber = if (!state) number.reversed() else number
    var numbersList by remember { mutableStateOf(listOf<String>()) }
    LaunchedEffect(state, number) {
        coroutineScope.launch {
            numbersList = PrintNumbers(correctNumber)
        }
        Logger.info("Результат $numbersList", "PrimeNumbers")
    }
    Text(
        buildAnnotatedString {
            if (numbersList.isNotEmpty()) {
                numbersList.forEach {
                    append(bullet)
                    append("\t\t")
                    append(it)
                    if (isPrime(it.toDouble())) {
                        append(" - prime")
                    }
                    append("\n")
                }
            }
        }
    )
}
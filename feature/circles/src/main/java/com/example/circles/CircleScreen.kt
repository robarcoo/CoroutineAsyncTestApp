package com.example.circles

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.example.data.doCirclesIntercept
import com.example.logger.Logger
import com.example.ui.components.TextComponent
import com.example.ui.components.fieldComponent
import kotlinx.coroutines.launch


@Composable
fun CircleScreen(navController: NavController) {
    val coroutineScope = rememberCoroutineScope()
    var x1 by remember { mutableStateOf("0") }
    var y1 by remember { mutableStateOf("0") }
    var r1 by remember { mutableStateOf("0") }
    var x2 by remember { mutableStateOf("0") }
    var y2 by remember { mutableStateOf("0") }
    var r2 by remember { mutableStateOf("0") }

    var result by remember { mutableStateOf("") }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        com.example.ui.components.BackToMenuButton(navController = navController)
        Column {
            TextComponent(text = "Первый круг")
            Column {
                x1 = fieldComponent("Координата X первого круга")
                y1 = fieldComponent("Координата Y первого круга")
                r1 = fieldComponent("Радиус первого круга")
            }
            TextComponent(text = "Второй круг")
            Column {
                x2 = fieldComponent("Координата X второго круга")
                y2 = fieldComponent("Координата Y второго круга")
                r2 = fieldComponent("Радиус второго круга")
            }
        }

        Button(
            onClick = {
                coroutineScope.launch {
                    Logger.info("Корутина началась", "Circles")
                    result =
                        doCirclesIntercept(listOf(x1, y1, r1, x2, y2, r2).map {
                            it.toFloatOrNull() ?: 0f
                        })
                }
                Logger.debug("Результат: $result", "Circles")
            }
        ) {
            TextComponent("Вычислить")
        }
        Row {
            TextComponent("Результат: $result")
        }
    }

}
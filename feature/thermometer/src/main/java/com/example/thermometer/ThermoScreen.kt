package com.example.thermometer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.data.getResult
import com.example.data.rightTemp
import com.example.ui.components.TextComponent
import com.example.ui.components.fieldComponent
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThermoScreen(navController: NavController) {
    val tempOptitons = listOf("Celsius", "Kelvin", "Fahrenheit")
    var selectedOption by remember { mutableStateOf(tempOptitons[0]) }
    var isMenuExpanded by remember { mutableStateOf(false) }
    var state by remember { mutableStateOf(true) }
    var temp by remember { mutableFloatStateOf(25f) }
    Column(
        modifier = Modifier.padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        com.example.ui.components.BackToMenuButton(navController = navController)

        ExposedDropdownMenuBox(expanded = isMenuExpanded,
            onExpandedChange = { isMenuExpanded = !isMenuExpanded }) {
            TextField(
                value = selectedOption,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isMenuExpanded) },
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(expanded = isMenuExpanded,
                onDismissRequest = { isMenuExpanded = false }) {
                tempOptitons.forEach { tempType ->
                    DropdownMenuItem(text = { Text(tempType) }, onClick = {
                        selectedOption = tempType
                        isMenuExpanded = false
                    })
                }
            }
        }

        Row {
            RadioButton(selected = state, onClick = {
                state = true
            })
            TextComponent(text = "Summer")
        }
        Row {
            RadioButton(selected = !state, onClick = {
                state = false
            })
            TextComponent(text = "Winter")
        }
        temp = fieldComponent(
            value = temp.toString(), text = "Введите температуру"
        ).toFloatOrNull() ?: 0f
        val rightTemp = rightTemp(temp, selectedOption)

        ThermoOutput(state = state, selectedOption = selectedOption, temp = rightTemp)
    }
}


@Composable
fun ThermoOutput(state: Boolean, selectedOption: String, temp: Float) {
    val coroutineScope = rememberCoroutineScope()
    var result by remember { mutableStateOf(Pair<String, String?>("", "")) }
    LaunchedEffect(state, selectedOption, temp) {
        coroutineScope.launch {
            result = getResult(state, selectedOption, temp)
        }
    }
    TextComponent(result.first)
    result.second?.let { TextComponent(it) }
}

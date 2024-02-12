package com.example.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import com.example.data.removeLeadingZeros

@Composable
fun fieldComponent(
    text: String,
    value: String = "",
    stringPattern: String = "-?\\d*\\.?\\d*"
): String {
    var newValue by remember { mutableStateOf(value) }
    val pattern = remember { Regex(stringPattern) }
    OutlinedTextField(
        value = newValue,
        onValueChange = {
            if (it.matches(pattern) && it.length <= 10) {
                newValue = removeLeadingZeros(it)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        label = { Text(text) }
    )
    return newValue
}

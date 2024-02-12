package com.example.highintensive

import android.app.AlertDialog
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.ui.components.TextComponent
import com.example.ui.components.buttonComponent
import com.example.ui.components.fieldComponent
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.data.swapList
import com.example.logger.Logger
import com.example.ui.components.BackToMenuButton


//факториал - целые, неотрицательные числа
//двоичный корень - неотрицательные числа, троичный корень - все можно
//логарифм - неотрицательные числа больше нуля
//возведение в двоичную и троичную степень - все можно
//простое число целые положительные числа


@Composable
fun HighIntensiveScreen(navController: NavController) {
    val numbersList = remember { mutableStateListOf("0", "0", "0", "0", "0") }
    val openDialog = remember { mutableStateOf(false) }
    val answersList = remember { mutableStateListOf("0", "0", "0", "0", "0", "0", "0", "0") }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BackToMenuButton(navController)
        Row {
            numbersList[0] = fieldComponent("Factorial", "0")
            buttonComponent("Run", 1, numbersList, answersList)
        }
        Row {
            TextComponent("Answer - ${answersList[0]}")
        }
        Row {
            numbersList[1] = fieldComponent("Square and cube root", "0")
            buttonComponent("Run", 2, numbersList, answersList)
        }
        Row {
            TextComponent("Square Root - ${answersList[1]} ")
            TextComponent("Cube Root - ${answersList[2]}")
        }
        Row {
            numbersList[2] = fieldComponent("Ln and Lg", "0")
            buttonComponent("Run", 3, numbersList, answersList)
        }
        Row {
            TextComponent("Lg - ${answersList[3]} ")
            TextComponent("Ln - ${answersList[4]} ")
        }
        Row {
            numbersList[3] = fieldComponent("Square and cube", "0")
            buttonComponent("Run", 4, numbersList, answersList)
        }
        Row {
            TextComponent("Square - ${answersList[5]} ")
            TextComponent("Cube - ${answersList[6]} ")
        }
        Row {
            numbersList[4] = fieldComponent("Primality test", "0")
            buttonComponent("Run", 5, numbersList, answersList)
        }
        Row {
            TextComponent("Is prime - ${answersList[7]} ")
        }
        buttonComponent("Run", 6, numbersList, answersList)
    }

    if (answersList.isNotEmpty() && answersList[7] == "null" && !openDialog.value) {
        answersList[7] = "0"
        openDialog.value = true
    }
    if (openDialog.value) {
        openDialog.value = false
        val alertDialog = AlertDialog.Builder(LocalContext.current)
            .setTitle("An error has occurred. Please try again.")
            .setMessage("Time out")
            .setNegativeButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        alertDialog.setOnShowListener {
            alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener {
                alertDialog.dismiss()
            }
        }
        alertDialog.show()
    }
}




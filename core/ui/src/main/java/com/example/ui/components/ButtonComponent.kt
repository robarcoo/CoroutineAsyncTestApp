package com.example.ui.components

import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.data.isPrime
import com.example.data.solveCube
import com.example.data.solveCubeRoot
import com.example.data.solveFactorial
import com.example.data.solveLn
import com.example.data.solveLog
import com.example.data.solveSquare
import com.example.data.solveSquareRoot
import com.example.data.swapList
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withTimeoutOrNull

@Composable
fun buttonComponent (text : String, option: Int, passedList: List<String>, answersList: SnapshotStateList<String>) {
    val coroutineScope = rememberCoroutineScope()
    var textButton by remember { mutableStateOf(text) }
    var job: Job? = null
    Button( onClick = {
        if (textButton == "Run") {
            job = coroutineScope.launch {
                textButton = "Cancel"
                solveCalculation(option, passedList.map { it.toDoubleOrNull() ?: 0.0 }, answersList)
                textButton = "Run"
            }

        } else {
            job?.cancel()
            textButton = "Run"
        }
    }) {
        TextComponent(text = textButton)
    }
}

suspend fun solveCalculation(option: Int, passedList: List<Double>, answersList: SnapshotStateList<String>) = coroutineScope {

    when (option) {
        1 -> answersList[0] = async { solveFactorial(passedList[0].toULong()).toString() }.await()
        2 -> {
            answersList[1] = async { solveSquareRoot(passedList[1]) }.await()
            answersList[2] = async { solveCubeRoot(passedList[1]) }.await()
        }
        3 -> {
            answersList[3] = async { solveLog(passedList[2]) }.await()
            answersList[4] = async { solveLn(passedList[2]) }.await()
        }
        4 -> {
            answersList[5] = async { solveSquare(passedList[3]) }.await()
            answersList[6] = async { solveCube(passedList[3])}.await()
        }
        5 -> { answersList[7] = async { withTimeoutOrNull(1000L) {    delay(1000L)
            isPrime(passedList[4]).toString()}.toString() }.await() }
        6 -> {
                answersList.swapList(
                    listOf(
                        async { solveFactorial(passedList[0].toULong()).toString() }.await(),
                        async { solveSquareRoot(passedList[1]) }.await(),
                        async { solveCubeRoot(passedList[1]) }.await(),
                        async { solveLog(passedList[2]) }.await(),
                        async { solveLn(passedList[2]) }.await(),
                        async { solveSquare(passedList[3]) }.await(),
                        async { solveCube(passedList[3]) }.await(),
                        async {
                            withTimeoutOrNull(1000L) {
                                isPrime(passedList[4]).toString()
                            }.toString()
                        }.await()
                    )
                )
            }
        else -> listOf("0", "0", "0", "0", "0", "0", "0", "0")
    }
}


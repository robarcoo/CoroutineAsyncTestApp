package com.example.data

import kotlin.math.ln
import kotlin.math.log
import kotlin.math.pow
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.logger.Logger

tailrec fun solveFactorial(number: ULong, pass: ULong = 1UL) : ULong {
    val answer = number * pass
    return if (number <= 1UL) {
        Logger.info("Looking for factorial", "Solve")
        answer
    } else {
        solveFactorial(number - 1UL, answer)
    }
}

fun solveSquareRoot(number: Double) : String {
    Logger.info("SquareRoot", "Solve")
    return "%.2f".format(number.pow(1.0/2.0))
}

fun solveCubeRoot(number: Double) : String {
    Logger.info("CubeRoot", "Solve")
    return "%.2f".format(number.pow(1.0/3.0))
}

fun solveLog(number: Double) : String {
    Logger.info("Log", "Solve")
    return "%.2f".format(log(number, 10.0))
}

fun solveLn(number: Double) : String {
    Logger.info("Ln", "Solve")
    return "%.2f".format(ln(number))
}

fun solveSquare(number: Double) : String {
    Logger.info("Square", "Solve")
    return number.pow(2.0).toString()
}

fun solveCube(number: Double) : String {
    Logger.info("Cube", "Solve")
    return number.pow(3.0).toString()
}

fun <T> SnapshotStateList<T>.swapList(newList: Collection<T>){
    if (this != newList && newList.isNotEmpty()) {
        clear()
        addAll(newList)
    }
}
package com.example.data

import kotlin.math.roundToInt


fun rightTemp(number: Float, mode: String): Float {
    return when (mode) {
        "Celsius" -> number
        "Kelvin" -> number + 273.15f
        "Fahrenheit" -> (number * 9 / 5) + 32
        else -> throw IllegalArgumentException("Mode chosen incorrectly")
    }
}

suspend fun getResult(state: Boolean, selectedOption: String, temp: Float): Pair<String, String?> {
    val summerMax = rightTemp(25.0f, selectedOption)
    val summerMin = rightTemp(22.0f, selectedOption)
    val winterMax = rightTemp(22.0f, selectedOption)
    val winterMin = rightTemp(20.0f, selectedOption)
    val modeChar = when (selectedOption) {
        "Celsius" -> "˚C"
        "Kelvin" -> " K"
        "Fahrenheit" -> "˚F"
        else -> throw IllegalArgumentException("Mode chosen incorrectly")
    }

    val comfortableTemperature = if (state) {
        "The comfortable temperature is from $summerMin to $summerMax $modeChar."
    } else {
        "The comfortable temperature is from $winterMin to $winterMax $modeChar."
    }

    val advice = if (state && temp !in summerMin..summerMax) {
        if (temp > summerMax) {
            "Please, make it cooler by ${(temp - summerMax).roundToInt()} degrees"
        } else {
            "Please, make it warmer by ${(summerMin - temp).roundToInt()} degrees"
        }
    } else if (!state && temp !in winterMin..winterMax) {
        if (temp > winterMax) {
            "Please, make it cooler by ${(temp - winterMax).roundToInt()} degrees"
        } else {
            "Please, make it warmer by ${(winterMin - temp).roundToInt()} degrees"
        }
    } else {
        null
    }

    return Pair(comfortableTemperature, advice)
}
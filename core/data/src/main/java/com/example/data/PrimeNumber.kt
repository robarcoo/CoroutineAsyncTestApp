package com.example.data


fun isPrime(number: Double): Boolean {
    if (number < 2) return false
    for (i in 2..kotlin.math.sqrt(number).toInt()) {
        if (number % i == 0.0) return false
    }
    return true
}

fun removeLeadingZeros(input: String): String {
    val result = input.replace("^(-?)0+".toRegex(), "$1")
    return if (result == "") "0" else if (result[0] == '.') "0$result" else { result }
}

suspend fun PrintNumbers(str_numb: String) : MutableList<String> {
    var str_answer = ""
    val result : MutableList<String> = mutableListOf()
    for (ch in str_numb.iterator()) {
        str_answer += ch
        str_answer = removeLeadingZeros(str_answer)
        result.add(str_answer)
    }
    return result
}
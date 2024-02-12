package com.example.data

import kotlin.math.sqrt

data class Circle(
    var x: Float = 0.0f,
    var y: Float = 0.0f,
    var r: Float = 0.0f
)

suspend fun doCirclesIntercept(circles: List<Float>): String {
    val c1 = Circle(circles[0], circles[1], circles[2])
    val c2 = Circle(circles[3], circles[4], circles[5])
    val d = sqrt(
        ((c1.x - c2.x) * (c1.x - c2.x)).toDouble()
                + ((c1.y - c2.y) * (c1.y - c2.y)).toDouble()
    )
    val r1 = (c1.r).toDouble()
    val r2 = (c2.r).toDouble()
    return when {
        d <= r1 - r2 -> "Circle B is inside A"
        d <= r2 - r1 -> "Circle A is inside B"
        d < r1 + r2 -> "Circles intersect each other"
        d == r1 + r2 -> "Circles touch each other"
        else -> "Circle do not touch each other"

    }
}
package com.example.logger

import android.util.Log

object Logger {
    private const val TAG = "Group00"

    fun error(message: String, tag: String = TAG) {
        Log.e(tag, message)
    }

    fun info(message: String, tag: String = TAG) {
        Log.i(tag, message)
    }

    fun warn(message: String, tag: String = TAG) {
        Log.w(tag, message)
    }

    fun debug(message: String, tag: String = TAG) {
        Log.d(tag, message)
    }
}
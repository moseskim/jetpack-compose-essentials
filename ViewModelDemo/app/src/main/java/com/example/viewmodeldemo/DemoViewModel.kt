package com.example.viewmodeldemo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

import java.lang.Exception
import kotlin.math.roundToInt

class DemoViewModel {
    var isFahrenheit by mutableStateOf(true)
    var result by mutableStateOf("")

    fun convertTemp(temp: String) {

        try {
            val tempInt = temp.toInt()

            if (isFahrenheit) {
                result = ((tempInt - 32) * 0.5556).roundToInt().toString()
            } else {
                result = ((tempInt * 1.8) + 32).roundToInt().toString()
            }
        } catch (e: Exception) {
            result = "Invalid Entry"
        }
    }

    fun switchChange() {
        isFahrenheit = !isFahrenheit
    }
}
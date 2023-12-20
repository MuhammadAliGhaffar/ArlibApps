package com.muhammadalighaffar.medicineapp.utilities

import android.content.Context
import android.widget.Toast
import androidx.compose.ui.graphics.Color
import kotlin.random.Random

fun internetIsConnected(): Boolean {
    return try {
        val command = "ping -c 1 google.com"
        Runtime.getRuntime().exec(command).waitFor() == 0
    } catch (e: Exception) {
        false
    }
}

fun generateRandomColor(): Color {
    val random = Random.Default
    return Color(random.nextFloat(), random.nextFloat(), random.nextFloat(), 1f)
}

fun toast(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}
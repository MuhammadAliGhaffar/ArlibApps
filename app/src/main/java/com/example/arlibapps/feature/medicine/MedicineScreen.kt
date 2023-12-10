package com.example.arlibapps.feature.medicine

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.arlibapps.data.model.Medicine
import com.google.gson.Gson

@Composable

fun MedicineScreen (
    navController: NavController,
    serializationMedicine:String?
) {
    val deserializeUserConfiguration = Gson().fromJson(serializationMedicine, Medicine::class.java)

    Text(text = "MedicineScreen ${deserializeUserConfiguration.name}")
}
package com.example.arlibapps.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.arlibapps.feature.dashboard.DashboardScreen
import com.example.arlibapps.feature.login.LoginScreen
import com.example.arlibapps.feature.medicine.MedicineScreen
import com.example.arlibapps.feature.registration.RegistrationScreen
import com.example.arlibapps.utilities.DASHBOARD_SCREEN
import com.example.arlibapps.utilities.LOGIN_SCREEN
import com.example.arlibapps.utilities.MEDICINE_SCREEN
import com.example.arlibapps.utilities.REGISTRATION_SCREEN

@Composable
fun NavigationHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = LOGIN_SCREEN) {
        composable(route = LOGIN_SCREEN) {
            LoginScreen(navController = navController)
        }
        composable(route = REGISTRATION_SCREEN) {
            RegistrationScreen(navController = navController)
        }
        composable(route = DASHBOARD_SCREEN) {
            DashboardScreen(navController = navController)
        }
        composable(route = MEDICINE_SCREEN) {
            MedicineScreen(navController = navController)
        }
    }
}
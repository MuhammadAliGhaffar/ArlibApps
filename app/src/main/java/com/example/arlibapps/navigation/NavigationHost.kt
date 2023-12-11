package com.example.arlibapps.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.arlibapps.feature.dashboard.DashboardScreen
import com.example.arlibapps.feature.login.LoginScreen
import com.example.arlibapps.feature.medicine.MedicineScreen
import com.example.arlibapps.utilities.DASHBOARD_SCREEN
import com.example.arlibapps.utilities.LOGIN_SCREEN
import com.example.arlibapps.utilities.MEDICINE_SCREEN

@Composable
fun NavigationHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = LOGIN_SCREEN) {
        composable(route = LOGIN_SCREEN) {
            LoginScreen(navController = navController)
        }
        composable(
            route = "$DASHBOARD_SCREEN/{username}/{email}",
            arguments = listOf(
                navArgument("username") { type = NavType.StringType },
                navArgument("email") { type = NavType.StringType })
        ) {
            val username = it.arguments?.getString("username")
            val email = it.arguments?.getString("email")
            DashboardScreen(navController = navController, username = username, email = email)
        }
        composable(
            route = "$MEDICINE_SCREEN/{medicine}",
            arguments = listOf(navArgument("medicine") { type = NavType.StringType })
        ) {
            val serializationMedicine = it.arguments?.getString("medicine")
            MedicineScreen(
                navController = navController,
                serializationMedicine = serializationMedicine
            )
        }
    }
}
package com.example.lohammy_vasquez_ap2_p1.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lohammy_vasquez_ap2_p1.presentation.edit.EditRegistroScreen
import com.example.lohammy_vasquez_ap2_p1.presentation.list.ListRegistroScreen

@Composable
fun RegistroNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Main
    ) {
        composable<Screen.Main> {
            ListRegistroScreen(
                onNew = {
                    navController.navigate(Screen.Second)
                }
            )
        }
        composable<Screen.Second> {
            EditRegistroScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}

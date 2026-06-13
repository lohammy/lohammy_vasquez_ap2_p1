package com.example.lohammy_vasquez_ap2_p1.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.lohammy_vasquez_ap2_p1.presentation.edit.EditAmonestacionScreen
import com.example.lohammy_vasquez_ap2_p1.presentation.list.ListAmonestacionScreen

@Composable
fun RegistroNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.AmonestacionList
    ) {
        composable<Screen.AmonestacionList> {
            ListAmonestacionScreen(
                onEdit = { id ->
                    navController.navigate(Screen.AmonestacionEdit(id))
                },
                onAdd = {
                    navController.navigate(Screen.AmonestacionEdit(0))
                }
            )
        }
        composable<Screen.AmonestacionEdit> { backStackEntry ->
            val args = backStackEntry.toRoute<Screen.AmonestacionEdit>()
            EditAmonestacionScreen(
                amonestacionId = args.amonestacionId,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}

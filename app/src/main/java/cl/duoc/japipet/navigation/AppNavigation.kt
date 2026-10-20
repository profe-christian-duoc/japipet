package cl.duoc.japipet.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import cl.duoc.japipet.model.UserRole
import cl.duoc.japipet.ui.screens.AdminHomeScreen
import cl.duoc.japipet.ui.screens.ClientHomeScreen
import cl.duoc.japipet.ui.screens.WelcomeScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
    ) {

        NavHost(
            navController = navController,
            startDestination = AppRoutes.WELCOME
        ) {
            composable(AppRoutes.WELCOME) {
                WelcomeScreen(
                    onLogin = { role ->
                        val destination = when (role) {
                            UserRole.ADMIN -> AppRoutes.ADMIN_HOME
                            UserRole.CLIENT -> AppRoutes.CLIENT_HOME
                        }
                        navController.navigate(destination) {
                            launchSingleTop = true
                        }
                    }
                )
            }

            composable(AppRoutes.ADMIN_HOME) {

                AdminHomeScreen(
                    onLogout = {
                        navController.popBackStack()
                    }
                )
            }

            composable(AppRoutes.CLIENT_HOME) {

                ClientHomeScreen(
                    onLogout = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}
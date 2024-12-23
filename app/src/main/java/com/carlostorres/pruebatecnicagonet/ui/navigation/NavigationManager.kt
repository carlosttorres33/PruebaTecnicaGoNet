package com.carlostorres.pruebatecnicagonet.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.carlostorres.pruebatecnicagonet.home.ui.HomeScreen
import com.carlostorres.pruebatecnicagonet.login.ui.LoginScreen
import com.carlostorres.pruebatecnicagonet.splash.ui.SplashScreen

@Composable
fun NavigationManager(
    isLogged: Boolean,
) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Splash
    ) {

        composable<Splash> {
            SplashScreen(
                onFinish = {
                    navController.popBackStack()
                    if (isLogged) {
                        navController.navigate(Home)
                    } else {
                        navController.navigate(Login)
                    }
                }
            )
        }

        composable<Login> {
            LoginScreen(
                onLoginSuccess = {
                    navController.popBackStack()
                    navController.navigate(Home)
                }
            )
        }

        composable<Home> {
            HomeScreen()
        }

    }

}
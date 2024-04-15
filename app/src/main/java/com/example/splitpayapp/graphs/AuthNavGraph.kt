package com.example.splitpayapp.graphs


import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.splitpayapp.Screens
import com.example.splitpayapp.views.SplashScreen
import com.example.splitpayapp.views.auth.LoginScreen
import com.example.splitpayapp.views.auth.ForgotScreen
import com.example.splitpayapp.views.auth.RegisterScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTH,
        startDestination = AuthScreen.Splash.route
    ) {
        composable(route = AuthScreen.Splash.route) {
            SplashScreen(navController)
        }
        composable(route = AuthScreen.Login.route) {
            LoginScreen(navController)
        }
        composable(route = AuthScreen.Register.route) {
            RegisterScreen(navController)
        }
        composable(route = AuthScreen.Forgot.route) {
            ForgotScreen(navController)
        }
    }
}

sealed class AuthScreen(val route: String) {
    object Splash: AuthScreen(route = "SPLASH")
    object Login: AuthScreen("LOGIN")
    object Register: AuthScreen("REGISTER")
    object Forgot: AuthScreen("FORGOT")
}
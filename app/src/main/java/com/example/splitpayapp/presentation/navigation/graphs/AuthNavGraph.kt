package com.example.splitpayapp.presentation.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.splitpayapp.presentation.navigation.Screens
import com.example.splitpayapp.presentation.googlesignin.model.GoogleAuthUiClient
import com.example.splitpayapp.presentation.view.auth.LoginScreen
import com.example.splitpayapp.presentation.view.auth.ForgotScreen
import com.example.splitpayapp.presentation.view.auth.RegisterScreen


fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
    googleAuthUiClient: GoogleAuthUiClient
) {
    navigation(
        route = Graph.AUTH,
        startDestination = Screens.LoginScreen.name
    ) {
        composable(route = Screens.LoginScreen.name) {
            LoginScreen(
                navController = navController, googleAuthUiClient
            )
        }
        composable(route = Screens.RegisterScreen.name) {
            RegisterScreen(navController)
        }
        composable(route = Screens.ForgotScreen.name) {
            ForgotScreen(navController)
        }
    }
}
package com.example.splitpayapp.presentation.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.splitpayapp.presentation.googlesignin.model.GoogleAuthUiClient
import com.example.splitpayapp.presentation.navigation.Screens
import com.example.splitpayapp.presentation.view.onboardingscreen.OnBoardingScreen
import com.example.splitpayapp.presentation.viewmodel.MainViewModel

@Composable
fun RootNavigationGraph(
    googleAuthUiClient: GoogleAuthUiClient,
    startDestination: String,
    mainViewModel: MainViewModel
) {
    val rootNavController = rememberNavController()

    NavHost(
        navController = rootNavController,
        startDestination = startDestination,
        route = Graph.ROOT
    ) {
        composable(Screens.OnBoardingScreen.name) {
            OnBoardingScreen(mainViewModel = mainViewModel)
        }
        composable(Graph.AUTH) {
            authNavGraph(navController = rootNavController, googleAuthUiClient)
        }
        authNavGraph(navController = rootNavController, googleAuthUiClient)
        composable(Graph.MAIN_NAV) {
            MainNavigationGraph(googleAuthUiClient, rootNavController = rootNavController)
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTH = "auth_graph"
    const val MAIN_NAV = "main_nav_graph"
    const val ADD_FRIEND = "add_friend_nav_graph"
}
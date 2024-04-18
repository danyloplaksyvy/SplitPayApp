package com.example.splitpayapp.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.splitpayapp.googlesignin.GoogleAuthUiClient

@Composable
fun RootNavigationGraph(googleAuthUiClient: GoogleAuthUiClient) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Graph.AUTH,
        route = Graph.ROOT
    ) {
        composable(Graph.AUTH) {
            authNavGraph(navController = navController, googleAuthUiClient)
        }
        authNavGraph(navController = navController, googleAuthUiClient)
            composable(Graph.MAIN_NAV) {
                MainNavigationGraph(googleAuthUiClient)
            }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTH = "auth_graph"
    const val MAIN_NAV = "main_nav_graph"
}
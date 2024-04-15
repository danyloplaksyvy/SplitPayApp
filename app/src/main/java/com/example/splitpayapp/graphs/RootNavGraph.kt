package com.example.splitpayapp.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.splitpayapp.Screens
import com.example.splitpayapp.views.main.FriendsScreen

@Composable
fun RootNavigationGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Graph.AUTH,
        route = Graph.ROOT
    ) {
        authNavGraph(navController = navController)
        composable(Graph.MAIN_NAV) {
            RootNavigationGraph()
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTH = "auth_graph"
    const val MAIN_NAV = "main_nav_graph"
}
package com.example.splitpayapp.graphs

import android.provider.ContactsContract
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.splitpayapp.Screens
import com.example.splitpayapp.googlesignin.GoogleAuthUiClient
import com.example.splitpayapp.views.main.FriendsScreen
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun RootNavigationGraph() {
    val navController = rememberNavController()
//    val isUserLoggedIn = remember { mutableStateOf(false) } // Initially not logged in
//
//    Firebase.auth.addAuthStateListener { auth ->
//        isUserLoggedIn.value = auth.currentUser != null // Update based on auth state
//    }

    NavHost(
        navController = navController,
        startDestination = Graph.AUTH,
        route = Graph.ROOT
    ) {
        authNavGraph(navController = navController)
//        if (isUserLoggedIn.value) {
            composable(Graph.MAIN_NAV) {
                MainNavigationGraph()
            }
//        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTH = "auth_graph"
    const val MAIN_NAV = "main_nav_graph"
}
package com.example.splitpayapp.presentation.navigation.graphs

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.splitpayapp.presentation.navigation.bottomnavigationbar.NavItem
import com.example.splitpayapp.presentation.googlesignin.model.GoogleAuthUiClient
import com.example.splitpayapp.presentation.view.main.AddExpenseScreen
import com.example.splitpayapp.presentation.view.main.friendsscreen.FriendsScreen
import com.example.splitpayapp.presentation.view.main.GroupsScreen
import com.example.splitpayapp.presentation.view.main.ProfileScreen
import com.example.splitpayapp.presentation.view.main.RecentActivityScreen
import com.example.splitpayapp.presentation.view.main.friendsscreen.friendsviewmodel.FriendsViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch


@Composable
fun MainNavigationGraph(
    googleAuthUiClient: GoogleAuthUiClient,
    rootNavController: NavHostController,
    navController: NavHostController,
) {

    val friendsViewModel = viewModel<FriendsViewModel>()

//    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    NavHost(
        navController = navController,
        startDestination = NavItem.Friends.route,
        route = Graph.MAIN_NAV
    ) {
        composable(route = NavItem.Friends.route) {
            FriendsScreen(
                onAddFriendButtonClick = {
                    navController.navigate(Graph.ADD_FRIEND)
                }, friendsViewModel = friendsViewModel
            )
        }
        composable(route = NavItem.Groups.route) {
            GroupsScreen()
        }
        composable(route = NavItem.AddExpense.route) {
            AddExpenseScreen()
        }
        composable(route = NavItem.Recent.route) {
            RecentActivityScreen()
        }
        composable(route = NavItem.Profile.route) {
            ProfileScreen(googleAuthUiClient, onSignOut = {
                scope.launch {
                    try {
                        googleAuthUiClient.signOut()
                        rootNavController.navigate(Graph.AUTH)
                    } catch (e: Exception) {
                        e.printStackTrace()
                        if (e is CancellationException) throw e
                    }
                }
            })
        }
        addFriendNavGraph(navController, friendsViewModel = friendsViewModel)
    }
}
package com.example.splitpayapp.presentation.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.splitpayapp.presentation.navigation.bottomnavigationbar.NavItem
import com.example.splitpayapp.presentation.googlesignin.GoogleAuthUiClient
import com.example.splitpayapp.presentation.view.main.AddExpenseScreen
import com.example.splitpayapp.presentation.view.main.friendsscreen.FriendsScreen
import com.example.splitpayapp.presentation.view.main.groupsscreen.GroupsScreen
import com.example.splitpayapp.presentation.view.main.ProfileScreen
import com.example.splitpayapp.presentation.view.main.RecentActivityScreen
import com.example.splitpayapp.presentation.view.main.friendsscreen.friendsrepository.FriendsRepository
import com.example.splitpayapp.presentation.view.main.friendsscreen.friendsviewmodel.FriendsViewModel
import com.example.splitpayapp.presentation.view.main.groupsscreen.groupsviewmodel.GroupsViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch


@Composable
fun MainNavigationGraph(
    googleAuthUiClient: GoogleAuthUiClient,
    rootNavController: NavHostController,
    navController: NavHostController,
) {

    val friendsViewModel = hiltViewModel<FriendsViewModel>()
    val groupsViewModel = hiltViewModel<GroupsViewModel>()

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
                },
                friendsViewModel = friendsViewModel
            )
        }
        composable(route = NavItem.Groups.route) {
            GroupsScreen(onAddGroupButtonClick = {
                navController.navigate(Graph.ADD_GROUP)
            }, groupsViewModel = groupsViewModel)
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
        addFriendNavGraph(navController, friendsViewModel)
        addGroupNavGraph(navController, groupsViewModel, friendsViewModel)
    }
}
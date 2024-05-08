package com.example.splitpayapp.presentation.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.splitpayapp.presentation.navigation.Screens
import com.example.splitpayapp.presentation.view.main.friendsscreen.components.AddFriendScreen

fun NavGraphBuilder.addFriendNavGraph(navController: NavHostController) {
    navigation(route = Graph.ADD_FRIEND, startDestination = AddFriend.ADD_FRIEND_SCREEN.route) {
        composable(route = AddFriend.ADD_FRIEND_SCREEN.route) {
            AddFriendScreen(onCancelClick = {
                navController.popBackStack()
            })
        }
    }
}

sealed class AddFriend(val route: String) {
    object ADD_FRIEND_SCREEN : AddFriend(route = "ADD_FRIEND_PAGE")
}


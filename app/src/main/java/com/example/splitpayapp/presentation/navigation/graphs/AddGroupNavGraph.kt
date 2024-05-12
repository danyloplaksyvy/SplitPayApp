package com.example.splitpayapp.presentation.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.splitpayapp.presentation.navigation.Screens
import com.example.splitpayapp.presentation.view.main.friendsscreen.components.AddFriendScreen
import com.example.splitpayapp.presentation.view.main.friendsscreen.friendsviewmodel.FriendsViewModel
import com.example.splitpayapp.presentation.view.main.groupsscreen.components.AddGroupScreen
import com.example.splitpayapp.presentation.view.main.groupsscreen.groupsviewmodel.GroupsViewModel

fun NavGraphBuilder.addGroupNavGraph(
    navController: NavHostController,
    groupsViewModel: GroupsViewModel
) {

    navigation(route = Graph.ADD_GROUP, startDestination = AddGroup.ADD_GROUP_SCREEN.route) {
        composable(route = AddGroup.ADD_GROUP_SCREEN.route) {
            AddGroupScreen(
                onCancelClick = {
                    navController.popBackStack()
                },
                onAddGroupClick = {
                    navController.navigate(Screens.GroupsScreen.name)
                },
                groupsViewModel = groupsViewModel
            )
        }
    }
}

sealed class AddGroup(val route: String) {
    object ADD_GROUP_SCREEN : AddGroup(route = "ADD_GROUP_PAGE")
}

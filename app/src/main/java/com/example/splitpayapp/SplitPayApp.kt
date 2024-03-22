package com.example.splitpayapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.splitpayapp.Screens.AddExpenseScreen
import com.example.splitpayapp.Screens.ArticleScreen
import com.example.splitpayapp.Screens.FriendsScreen
import com.example.splitpayapp.Screens.GroupsScreen
import com.example.splitpayapp.Screens.RecentActivityScreen

@Composable
fun SplitPayApp(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.FriendsScreen.route) {
        composable(Screen.FriendsScreen.route) {
            FriendsScreen(
                navigationToFriendsScreen = {},
                navigationToGroupsScreen = {
                    navController.navigate(Screen.GroupsScreen.route)
                },
                navigationToAddExpenseScreen = {
                    navController.navigate(Screen.AddExpenseScreen.route)
                },
                navigationToRecentActivityScreen = {
                    navController.navigate(Screen.RecentActivityScreen.route)
                },
                navigationToArticleScreen = {
                    navController.navigate(Screen.ArticleScreen.route)
                }
            )
        }
        composable(Screen.GroupsScreen.route) {
            GroupsScreen(
                navigationToFriendsScreen = {
                    navController.navigate(Screen.FriendsScreen.route)
                },
                navigationToGroupsScreen = {},
                navigationToAddExpenseScreen = {
                    navController.navigate(Screen.AddExpenseScreen.route)
                },
                navigationToRecentActivityScreen = {
                    navController.navigate(Screen.RecentActivityScreen.route)
                },
                navigationToArticleScreen = {
                    navController.navigate(Screen.ArticleScreen.route)
                }
            )
        }
        composable(Screen.AddExpenseScreen.route) {
            AddExpenseScreen(
                navigationToFriendsScreen = {
                    navController.navigate(Screen.FriendsScreen.route)
                },
                navigationToGroupsScreen = {
                    navController.navigate(Screen.GroupsScreen.route)
                },
                navigationToAddExpenseScreen = {},
                navigationToRecentActivityScreen = {
                    navController.navigate(Screen.RecentActivityScreen.route)
                },
                navigationToArticleScreen = {
                    navController.navigate(Screen.ArticleScreen.route)
                }
            )
        }
        composable(Screen.RecentActivityScreen.route) {
            RecentActivityScreen(
                navigationToFriendsScreen = {
                    navController.navigate(Screen.FriendsScreen.route)
                },
                navigationToGroupsScreen = {
                    navController.navigate(Screen.GroupsScreen.route)
                },
                navigationToAddExpenseScreen = {
                    navController.navigate(Screen.AddExpenseScreen.route)
                },
                navigationToRecentActivityScreen = {},
                navigationToArticleScreen = {
                    navController.navigate(Screen.ArticleScreen.route)
                }
            )
        }
        composable(Screen.ArticleScreen.route) {
            ArticleScreen(
                navigationToFriendsScreen = {
                    navController.navigate(Screen.FriendsScreen.route)
                },
                navigationToGroupsScreen = {
                    navController.navigate(Screen.GroupsScreen.route)
                },
                navigationToAddExpenseScreen = {
                    navController.navigate(Screen.AddExpenseScreen.route)
                },
                navigationToRecentActivityScreen = {
                    navController.navigate(Screen.RecentActivityScreen.route)
                },
                navigationToArticleScreen = {}
            )
        }
    }
}
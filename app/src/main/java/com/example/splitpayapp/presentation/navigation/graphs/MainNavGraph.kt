package com.example.splitpayapp.presentation.navigation.graphs

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.splitpayapp.presentation.navigation.bottomnavigationbar.NavItem
import com.example.splitpayapp.presentation.googlesignin.GoogleAuthUiClient
import com.example.splitpayapp.presentation.navigation.Screens
import com.example.splitpayapp.presentation.view.main.addexpensescreen.AddExpenseScreen
import com.example.splitpayapp.presentation.view.main.friendsscreen.FriendsScreen
import com.example.splitpayapp.presentation.view.main.groupsscreen.GroupsScreen
import com.example.splitpayapp.presentation.view.main.articlesscreen.ArticlesScreen
import com.example.splitpayapp.presentation.view.main.addexpensescreen.expenseviewmodel.ExpenseViewModel
import com.example.splitpayapp.presentation.view.main.articlesscreen.articleviewmodel.ArticleViewModel
import com.example.splitpayapp.presentation.view.main.recentactivityscreen.RecentActivityScreen
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
    val expenseViewModel = hiltViewModel<ExpenseViewModel>()
    val articleViewModel = hiltViewModel<ArticleViewModel>()

    val context = LocalContext.current
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
            AddExpenseScreen(
                friendsViewModel = friendsViewModel,
                groupsViewModel = groupsViewModel,
                expenseViewModel = expenseViewModel,
                onCreateExpense = {
                    navController.navigate(Screens.FriendsScreen.name)
                    Toast.makeText(context, "Expense", Toast.LENGTH_LONG).show()
                })
        }
        composable(route = NavItem.Recent.route) {
            RecentActivityScreen()
        }
        composable(route = NavItem.Profile.route) {
            ArticlesScreen(googleAuthUiClient, onSignOut = {
                scope.launch {
                    try {
                        googleAuthUiClient.signOut()
                        rootNavController.navigate(Graph.AUTH)
                    } catch (e: Exception) {
                        e.printStackTrace()
                        if (e is CancellationException) throw e
                    }
                }
            }, onAddArticleButtonClicked = {
                navController.navigate(Graph.ADD_ARTICLE)
            })
        }
        addFriendNavGraph(navController, friendsViewModel)
        addGroupNavGraph(navController, groupsViewModel, friendsViewModel)
        addArticleNavGraph(navController, articleViewModel)
    }
}
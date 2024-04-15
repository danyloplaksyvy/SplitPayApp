package com.example.splitpayapp.graphs

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.splitpayapp.NavItem
import com.example.splitpayapp.views.main.AddExpenseScreen
import com.example.splitpayapp.views.main.FriendsScreen
import com.example.splitpayapp.views.main.GroupsScreen
import com.example.splitpayapp.views.main.ProfileScreen
import com.example.splitpayapp.views.main.RecentActivityScreen


@Composable
fun MainNavigationGraph() {

    val screens = listOf(
        NavItem.Friends,
        NavItem.Groups,
        NavItem.AddExpense,
        NavItem.Recent,
        NavItem.Profile
    )

    val navController = rememberNavController()
    // Remember user's actions
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    Scaffold(
        bottomBar = {
            NavigationBar {

                screens.forEach {screen ->
                    val isSelected =
                        currentDestination?.hierarchy?.any { it.route == screen.route } == true
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = false
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = screen.icon,
                                contentDescription = null,
                                tint = if (isSelected) Color(63, 99, 203) else screen.color,
                                modifier = Modifier.size(screen.size)
                            )
                        },
                        label = {
                            Text(
                                text = screen.text,
                                color = if (isSelected) Color(63, 99, 203) else screen.color
                            )
                        })
                }
            }
        }) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = NavItem.Friends.route,
            route = Graph.MAIN_NAV,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(route = NavItem.Friends.route) {
                FriendsScreen()
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
                ProfileScreen()
            }
        }
    }
}
package com.example.splitpayapp.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.splitpayapp.views.AddExpenseScreen
import com.example.splitpayapp.Screens
import com.example.splitpayapp.views.FriendsScreen
import com.example.splitpayapp.views.GroupsScreen
import com.example.splitpayapp.views.ProfileScreen
import com.example.splitpayapp.views.RecentActivityScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {

    val screenTitles = mapOf(
        Screens.FriendsScreen.name to "Friends",
        Screens.GroupsScreen.name to "Groups",
        Screens.ProfileScreen.name to "Profile",
        Screens.AddExpenseScreen.name to "Add Expense",
        Screens.RecentActivityScreen.name to "Recent Activity"
    )

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    Scaffold(
//        topBar = {
//            TopAppBar(title = {
//                Text(
//                    text = screenTitles[currentDestination?.route] ?: "Screen Title"
//                )
//            }, actions = {
//                TextButton(onClick = {  }) {
//                    Text(text = "Add friend ")
//                    Icon(imageVector = Icons.Default.PersonAdd, contentDescription = "Add a friend")
//                }
//            })
//        },
        bottomBar = {
            NavigationBar {
                // Remember user's actions

                // Change color on Active Screen
                listOfNavItems.forEach { navItem ->
                    val isSelected =
                        currentDestination?.hierarchy?.any { it.route == navItem.route } == true
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = {
                            navController.navigate(navItem.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = false
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = navItem.icon,
                                contentDescription = null,
                                tint = if (isSelected) Color(63, 99, 203) else navItem.color,
                                modifier = Modifier.size(navItem.size)
                            )
                        },
                        label = {
                            Text(
                                text = navItem.text,
                                color = if (isSelected) Color(63, 99, 203) else navItem.color
                            )
                        })
                }
            }
        }) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screens.FriendsScreen.name,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(route = Screens.FriendsScreen.name) {
                FriendsScreen()
            }
            composable(route = Screens.GroupsScreen.name) {
                GroupsScreen()
            }
            composable(route = Screens.AddExpenseScreen.name) {
                AddExpenseScreen()
            }
            composable(route = Screens.RecentActivityScreen.name) {
                RecentActivityScreen()
            }
            composable(route = Screens.ProfileScreen.name) {
                ProfileScreen()
            }
        }
    }
}
package com.example.splitpayapp.presentation.navigation.bottomnavigationbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.splitpayapp.presentation.googlesignin.GoogleAuthUiClient
import com.example.splitpayapp.presentation.navigation.graphs.MainNavigationGraph

@Composable
fun BottomNavigationBarScreen(
    navController: NavHostController = rememberNavController(),
    googleAuthUiClient: GoogleAuthUiClient,
    rootNavController: NavHostController
) {
    val screens = listOf(
        NavItem.Friends,
        NavItem.Groups,
        NavItem.AddExpense,
        NavItem.Recent,
        NavItem.Profile
    )

    // Remember user's actions
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    Scaffold(
        bottomBar = {

            val bottomBarDestination = screens.any { it.route == currentDestination?.route }
            if (bottomBarDestination) {
                NavigationBar(modifier = Modifier.shadow(15.dp)) {

                    screens.forEach { screen ->
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
                            },
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = Color(255, 255, 255, 255)
                            )
                        )
                    }
                }
            }
        }) { paddingValues ->
//        var modifier = Modifier.padding(paddingValues)
        Column(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()) {
            MainNavigationGraph(
                googleAuthUiClient = googleAuthUiClient,
                navController = navController,
                rootNavController = rootNavController,
            )
        }
    }
}
package com.example.splitpayapp.presentation.navigation.bottomnavigationbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Article
import androidx.compose.material.icons.outlined.Article
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.sharp.Add
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.splitpayapp.presentation.navigation.Screens

sealed class NavItem(
    val text: String,
    val icon: ImageVector,
    val route: String,
    val color: Color = Color(110, 103, 117, 255),
    val size: Dp = 24.dp,
) {
    object Friends : NavItem(
        text = "Friends",
        icon = Icons.Outlined.Group,
        route = Screens.FriendsScreen.name,
    )

    object Groups : NavItem(
        text = "Groups",
        icon = Icons.Outlined.Groups,
        route = Screens.GroupsScreen.name,
    )

    object AddExpense : NavItem(
        text = "Expense",
        icon = Icons.Sharp.Add,
        route = Screens.AddExpenseScreen.name,
    )

    object Recent : NavItem(
        text = "Recent",
        icon = Icons.Outlined.Email,
        route = Screens.RecentActivityScreen.name,
    )

    object Profile : NavItem(
        text = "Articles",
        icon = Icons.AutoMirrored.Outlined.Article,
        route = Screens.ProfileScreen.name,
    )

}

package com.example.splitpayapp.graphs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.MonetizationOn
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.splitpayapp.Screens

sealed class NavItem(
    val text: String,
    val icon: ImageVector,
    val route: String,
    val color: Color = Color.Unspecified,
    val size: Dp = 24.dp
) {
    object Friends : NavItem(
        text = "Friends",
        icon = Icons.Outlined.Group,
        route = Screens.FriendsScreen.name,
        color = Color(110, 103, 117, 255)
    )

    object Groups : NavItem(
        text = "Groups",
        icon = Icons.Outlined.Groups,
        route = Screens.GroupsScreen.name,
        color = Color(110, 103, 117, 255)
    )

    object AddExpense : NavItem(
        text = "",
        icon = Icons.Outlined.MonetizationOn,
        route = Screens.AddExpenseScreen.name,
        color = Color(101, 98, 179, 255),
        size = 48.dp
    )

    object Recent : NavItem(
        text = "Recent",
        icon = Icons.Outlined.Email,
        route = Screens.RecentActivityScreen.name,
        color = Color(110, 103, 117, 255)
    )

    object Profile : NavItem(
        text = "Profile",
        icon = Icons.Outlined.Settings,
        route = Screens.ProfileScreen.name,
        color = Color(110, 103, 117, 255)
    )

}
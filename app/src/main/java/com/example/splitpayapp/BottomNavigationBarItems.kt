package com.example.splitpayapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.MonetizationOn
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.sharp.Add
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

sealed class NavItem(
    val text: String,
    val icon: ImageVector,
    val route: String,
    val color: Color = Color.Unspecified,
    val size: Dp = 24.dp,
    val backgroundColor: Color = Color.Unspecified
) {
    object Friends : NavItem(
        text = "Friends",
        icon = Icons.Outlined.Group,
        route = Screens.FriendsScreen.name,
        color = Color(110, 103, 117, 255),
    )

    object Groups : NavItem(
        text = "Groups",
        icon = Icons.Outlined.Groups,
        route = Screens.GroupsScreen.name,
        color = Color(110, 103, 117, 255)
    )

    object AddExpense : NavItem(
        text = "Expense",
        icon = Icons.Sharp.Add,
        route = Screens.AddExpenseScreen.name,
        color = Color(63, 99, 203),
        backgroundColor = Color(242, 244, 252, 255)
//        size = 32.dp
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

package com.example.splitpayapp.presentation.view.main.groupsscreen.components


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AirplanemodeActive
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Healing
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Work
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

sealed class CategoryGroupItem(
    val color: Color = Color(5, 9, 21),
    val icon: ImageVector,
    val size: Dp = 24.dp,
    val name: String
) {
    data object Any: CategoryGroupItem(
        icon = Icons.Default.AttachMoney,
        name = "Any"
    )
    data object Travel: CategoryGroupItem(
        icon = Icons.Default.AirplanemodeActive,
        name = "Travel"
    )
    data object Home: CategoryGroupItem(
        icon = Icons.Default.Home,
        name = "Home"
    )
    data object Education: CategoryGroupItem(
        icon = Icons.Default.School,
        name = "Education"
    )
    data object Health: CategoryGroupItem(
        icon = Icons.Default.Healing,
        name = "Health"
    )
    data object Bills: CategoryGroupItem(
        icon = Icons.Default.Money,
        name = "Bills"
    )
    data object Work: CategoryGroupItem(
        icon = Icons.Default.Work,
        name = "Work"
    )
}
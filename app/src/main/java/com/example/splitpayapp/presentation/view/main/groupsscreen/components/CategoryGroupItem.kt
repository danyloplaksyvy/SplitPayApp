package com.example.splitpayapp.presentation.view.main.groupsscreen.components


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AirplanemodeActive
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Healing
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.outlined.AirplanemodeActive
import androidx.compose.material.icons.outlined.Healing
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Money
import androidx.compose.material.icons.outlined.School
import androidx.compose.material.icons.outlined.Work
import androidx.compose.material.icons.twotone.AirplanemodeActive
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
    object Any: CategoryGroupItem(
        icon = Icons.Default.AttachMoney,
        name = "Any"
    )
    object Travel: CategoryGroupItem(
        icon = Icons.Default.AirplanemodeActive,
        name = "Travel"
    )
    object Home: CategoryGroupItem(
        icon = Icons.Default.Home,
        name = "Home"
    )
    object Education: CategoryGroupItem(
        icon = Icons.Default.School,
        name = "Education"
    )
    object Health: CategoryGroupItem(
        icon = Icons.Default.Healing,
        name = "Health"
    )
    object Bills: CategoryGroupItem(
        icon = Icons.Default.Money,
        name = "Bills"
    )
    object Work: CategoryGroupItem(
        icon = Icons.Default.Work,
        name = "Work"
    )
}
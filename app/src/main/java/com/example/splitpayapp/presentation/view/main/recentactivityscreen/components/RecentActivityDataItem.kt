package com.example.splitpayapp.presentation.view.main.recentactivityscreen.components

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AirplanemodeActive
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.splitpayapp.presentation.view.main.groupsscreen.components.CategoryGroupItem

sealed class RecentActivityDataItem(
    val title: String,
    val categoryIcon: ImageVector,
    val action: String,
    val date: String
) {
    data object first: RecentActivityDataItem(
        title = "Real Talk",
        categoryIcon = Icons.Default.AirplanemodeActive,
        action = "Created the group",
        date = "3 June at 17:52"
    )
    data object second: RecentActivityDataItem(
        title = "Max",
        categoryIcon = Icons.Default.Person,
        action = "Added the friend",
        date = "1 June at 12:00"
    )
}
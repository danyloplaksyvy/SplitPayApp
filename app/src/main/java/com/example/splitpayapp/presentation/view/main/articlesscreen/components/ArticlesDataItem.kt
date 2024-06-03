package com.example.splitpayapp.presentation.view.main.articlesscreen.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AirplanemodeActive
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ArticlesDataItem(
    val title: String,
    val categoryIcon: ImageVector,
    val action: String,
) {
    data object first: ArticlesDataItem(
        title = "Travel",
        categoryIcon = Icons.Default.AirplanemodeActive,
        action = "Danya, Max"
    )
    data object second: ArticlesDataItem(
        title = "Bills",
        categoryIcon = Icons.Default.Money,
        action = "Danya, Johny, Bob",
    )
}
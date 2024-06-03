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
    val dateStart: String,
    val dateEnd: String
) {
    data object first: ArticlesDataItem(
        title = "Travel",
        categoryIcon = Icons.Default.AirplanemodeActive,
        action = "Danya, Max",
        dateStart = "23 May",
        dateEnd = "3 June"
    )
    data object second: ArticlesDataItem(
        title = "Bills",
        categoryIcon = Icons.Default.Money,
        action = "Danya, Johny, Bob",
        dateStart = "1 June",
        dateEnd = "3 June"
    )
}
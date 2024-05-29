package com.example.splitpayapp.presentation.view.main.friendsscreen.components

import com.example.splitpayapp.presentation.view.main.addexpensescreen.components.Expense

data class Friend(
    val id: String = "",
    val name: String = "",
    val email: String? = null,
    val image: String = "https://picsum.photos/200",
    var isDeleting: Boolean = false,
    var expense: List<Expense> = emptyList()
)
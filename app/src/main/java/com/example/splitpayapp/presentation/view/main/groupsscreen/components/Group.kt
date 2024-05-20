package com.example.splitpayapp.presentation.view.main.groupsscreen.components

data class Group(
    val id: String = "",
    val name: String = "",
    val category: String = "",
    val image: String = "https://picsum.photos/200",
    var isDeleting: Boolean = false
)
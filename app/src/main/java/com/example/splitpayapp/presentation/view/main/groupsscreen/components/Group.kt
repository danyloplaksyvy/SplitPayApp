package com.example.splitpayapp.presentation.view.main.groupsscreen.components

import com.example.splitpayapp.presentation.view.main.friendsscreen.components.Friend

data class Group(
    val id: String = "",
    val name: String = "",
    val category: String = "",
    val image: String = "https://picsum.photos/200",
    var isDeleting: Boolean = false,
    var members: List<Friend> = emptyList()
)
package com.example.splitpayapp.presentation.view.main.groupsscreen.components

data class Group(
    val id: Int,
    val name: String,
    var isDeleting: Boolean = false
)
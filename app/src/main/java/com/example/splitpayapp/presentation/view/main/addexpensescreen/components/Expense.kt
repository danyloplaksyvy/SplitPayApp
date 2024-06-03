package com.example.splitpayapp.presentation.view.main.addexpensescreen.components

data class Expense(
    val id: String = "",
    val name: String = "",
    val sum: Float = 0f,
    val typeExpense: String = "",
    val category: String = "",
    val groupId: String = "",
    val participants: List<String> = emptyList(), // User IDs or friend IDs
    val amounts: List<Float> = emptyList() // Amounts per participant
)

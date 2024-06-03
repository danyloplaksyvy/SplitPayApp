package com.example.splitpayapp.presentation.view.main.addexpensescreen.expenserepository

import com.example.splitpayapp.presentation.view.main.addexpensescreen.components.Expense
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExpenseRepository @Inject constructor() {
    private val db = Firebase.firestore

    suspend fun addExpense(expense: Expense) {
        val userId = Firebase.auth.currentUser?.uid ?: return // Get user ID

        // Get a new document reference with an auto-generated ID
        val newExpenseRef = db.collection("users")
            .document(userId)
            .collection("expenses")
            .document()

        newExpenseRef.set(expense.copy(id = newExpenseRef.id)).await()
    }
}
package com.example.splitpayapp.presentation.view.main.addexpensescreen.expenseviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.splitpayapp.presentation.view.main.addexpensescreen.components.Expense
import com.example.splitpayapp.presentation.view.main.addexpensescreen.expenserepository.ExpenseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class ExpenseViewModel @Inject constructor(
    private val repository: ExpenseRepository
) : ViewModel() {
    fun addExpense(expense: Expense) = viewModelScope.launch {
        repository.addExpense(expense)
        // TODO -> error handling and success feedback here
    }
}
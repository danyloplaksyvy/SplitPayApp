package com.example.splitpayapp.presentation.view.auth.authviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class AuthViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    private val _loginResult = Channel<Boolean>()
    val loginResult = _loginResult.receiveAsFlow()

    private val _registerResult = Channel<Boolean>()
    val registerResult = _registerResult.receiveAsFlow()

    private val _passwordResetResult = Channel<Boolean>() // Create a Channel
    val passwordResetResult = _passwordResetResult.receiveAsFlow() // Expose as a Flow

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password).await()
                _loginResult.send(true)
            } catch (e: Exception) {
                _loginResult.send(false)
            }
        }
    }

    fun registerUser(email: String, password: String) {
        viewModelScope.launch {
            try {
                auth.createUserWithEmailAndPassword(email, password).await()
                _registerResult.send(true)
            } catch (e: Exception) {
                _registerResult.send(false)
            }
        }
    }

    fun sendPasswordResetEmail(email: String) {
        viewModelScope.launch {
            try {
                auth.sendPasswordResetEmail(email).await()
                _passwordResetResult.send(true) // Indicate success
            } catch (e: Exception) {
                _passwordResetResult.send(false) // Indicate failure
            }
        }
    }
}
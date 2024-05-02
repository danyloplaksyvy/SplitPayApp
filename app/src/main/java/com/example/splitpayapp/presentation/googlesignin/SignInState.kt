package com.example.splitpayapp.presentation.googlesignin

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)


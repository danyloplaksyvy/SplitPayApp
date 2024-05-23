package com.example.splitpayapp.presentation.view.auth

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.splitpayapp.presentation.navigation.Screens
import com.example.splitpayapp.presentation.navigation.graphs.Graph
import com.example.splitpayapp.presentation.view.auth.authviewmodel.AuthViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun RegisterScreen(
    navController: NavController,
    authViewModel: AuthViewModel = viewModel()
) {
    val context = LocalContext.current
    val nameFieldState = remember { mutableStateOf("") }
    val emailFieldState = remember { mutableStateOf("") }
    val passwordFieldState = remember { mutableStateOf("") }
    val confirmPasswordFieldState = remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) } // For loading state
    var passwordVisibility by remember { mutableStateOf(false) }

    // Collect the registration result from the ViewModel using LaunchedEffect
    LaunchedEffect(key1 = authViewModel.registerResult) {
        authViewModel.registerResult.collect { isSuccess ->
            isLoading = false
            if (isSuccess) {
                navController.navigate(Graph.MAIN_NAV) {
                    popUpTo(Screens.RegisterScreen.name) { inclusive = true }
                }
            } else {
                val errorMessage = "Sign-up failed." // Customize error message based on the exception from the ViewModel
                Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Sign Up",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 32.dp, start = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Name
            OutlinedTextField(
                value = nameFieldState.value,
                onValueChange = { nameFieldState.value = it },
                label = { Text("Full Name") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                trailingIcon = {
                    Icon(
                        Icons.Outlined.Person,
                        contentDescription = null
                    )
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
            // Email
            OutlinedTextField(
                value = emailFieldState.value,
                onValueChange = { emailFieldState.value = it },
                label = { Text("Email") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                trailingIcon = {
                    Icon(
                        Icons.Outlined.Email,
                        contentDescription = null
                    )
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )

            // Password
            OutlinedTextField(
                value = passwordFieldState.value,
                onValueChange = { passwordFieldState.value = it },
                label = { Text("Password") },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                trailingIcon = {
                    IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                        Icon(
                            imageVector = if (passwordVisibility) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility,
                            contentDescription = null
                        )
                    }
                },
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Next),
            )

            // Confirm Password
            OutlinedTextField(
                value = confirmPasswordFieldState.value,
                onValueChange = { confirmPasswordFieldState.value = it },
                label = { Text("Confirm password") },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                trailingIcon = {
                    IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                        Icon(
                            imageVector = if (passwordVisibility) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility,
                            contentDescription = null
                        )
                    }
                },
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done)
            )

            Button(
                onClick = {
                    val email = emailFieldState.value.trim()
                    val password = passwordFieldState.value.trim()
                    val confirmPassword = confirmPasswordFieldState.value.trim()

                    if (confirmPassword != password) {
                        Toast.makeText(context, "Passwords must be the same", Toast.LENGTH_LONG)
                            .show()
                    }

                    if (email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
                        Toast.makeText(
                            context,
                            "Please fill in both email and password",
                            Toast.LENGTH_LONG
                        ).show()
                        return@Button
                    }
                    isLoading = true // Show loading State
                    // Adding User to Auth
                    authViewModel.registerUser(email, password)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text("Continue")
            }

            TextButton(
                onClick = {
                    /*Switch(navigate between screens)*/
                    navController.navigate(Screens.LoginScreen.name)
                }, modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) { // Switch mode
                Text("Already have an account? Login")
            }
        }
    }
}

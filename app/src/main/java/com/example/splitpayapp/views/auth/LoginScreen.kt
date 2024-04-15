package com.example.splitpayapp.views.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.splitpayapp.graphs.AuthScreen
import com.example.splitpayapp.graphs.Graph
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun LoginScreen(navController: NavController) {
    val nameFieldState = remember { mutableStateOf("") } // For name field in sign-up
    val emailFieldState = remember { mutableStateOf("") }
    val passwordFieldState = remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) } // For loading state
    var errorMessage by remember { mutableStateOf("") }  // For error display

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Sign In",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )

        OutlinedTextField(
            value = emailFieldState.value,
            onValueChange = { emailFieldState.value = it },
            label = { Text("Email") },
            modifier = Modifier.padding(8.dp)
        )

        OutlinedTextField(
            value = passwordFieldState.value,
            onValueChange = { passwordFieldState.value = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(), // Obscure password
            modifier = Modifier.padding(8.dp)
        )

        Button(
            onClick = {
                isLoading = true // Show loading State
                errorMessage = "" // Clear previous error if it was

                val email = emailFieldState.value
                val password = passwordFieldState.value
                val name = nameFieldState.value

                Firebase.auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        isLoading = false
                        if (task.isSuccessful) {
                            navController.navigate(Graph.MAIN_NAV) {
                                popUpTo(AuthScreen.Login.route) {
                                    inclusive = true
                                } // Clear auth from backstack
                            }
                        } else {
                            errorMessage = task.exception?.message ?: "Sign-up failed"
                        }
                    }
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Sign In")
        }

        TextButton(
            onClick = {
                /*Switch*/
                navController.navigate(AuthScreen.Register.route)

            },
            modifier = Modifier.padding(16.dp)
        ) { // Switch mode
            Text("Switch to Sign Up")
        }

        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center
            )
        }
        // Add Google Sign-In (We'll cover this later)
    }
}
package com.example.splitpayapp.views.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.splitpayapp.Screens
import com.example.splitpayapp.graphs.Graph
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun RegisterScreen(navController: NavController) {
    val nameFieldState = remember { mutableStateOf("") } // For name field in sign-up
    val emailFieldState = remember { mutableStateOf("") }
    val passwordFieldState = remember { mutableStateOf("") }
    val confirmPasswordFieldState = remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) } // For loading state
    var errorMessage by remember { mutableStateOf("") }  // For error display
    var passwordVisibility by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Sign Up",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )


//        OutlinedTextField(
//            value = nameFieldState.value,
//            onValueChange = { nameFieldState.value = it },
//            label = { Text("Name") },
//            modifier = Modifier.padding(8.dp)
//        )

        OutlinedTextField(
            value = emailFieldState.value,
            onValueChange = { emailFieldState.value = it },
            label = { Text("Email") },
            modifier = Modifier.padding(8.dp)
        )

        // Password
        OutlinedTextField(
            value = passwordFieldState.value,
            onValueChange = { passwordFieldState.value = it },
            label = { Text("Password") },
            modifier = Modifier.padding(8.dp),
            trailingIcon = {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(
                        imageVector = if (passwordVisibility) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = null
                    )
                }
            },
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        )

        // Confirm Password
        OutlinedTextField(
            value = confirmPasswordFieldState.value,
            onValueChange = { confirmPasswordFieldState.value = it },
            label = { Text("Confirm password") },
            modifier = Modifier.padding(8.dp),
            trailingIcon = {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(
                        imageVector = if (passwordVisibility) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = null
                    )
                }
            },
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Button(
            onClick = {
                isLoading = true // Show loading State
                errorMessage = "" // Clear previous error if it was

                val email = emailFieldState.value
                val password = passwordFieldState.value
                val name = nameFieldState.value

                Firebase.auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        isLoading = false
                        if (task.isSuccessful) {
                            //Sign-up success (navigate to the main app area)

                            navController.navigate(Graph.MAIN_NAV) {
                                popUpTo(Screens.RegisterScreen.name) {
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
            Text("Sign Up")
        }

        TextButton(
            onClick = {
                /*Switch(navigate between screens)*/
                navController.navigate(Screens.LoginScreen.name)
            },
            modifier = Modifier.padding(16.dp)
        ) { // Switch mode
            Text("Switch to Sign In")
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

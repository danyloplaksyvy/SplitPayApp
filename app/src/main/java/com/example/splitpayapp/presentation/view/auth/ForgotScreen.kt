package com.example.splitpayapp.presentation.view.auth

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.splitpayapp.R
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotScreen(navController: NavController) {
    val context = LocalContext.current // For Toasts
    var emailFieldState by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Forgot Password?") },
                actions = {
                    IconButton(onClick = { navController.popBackStack() }) {

                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
//            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp)
            ) {

                // Email
                OutlinedTextField(
                    value = emailFieldState,
                    onValueChange = { emailFieldState = it },
                    label = { Text("Email") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Done
                    ),
                    trailingIcon = {
                        Icon(
                            Icons.Outlined.Email,
                            contentDescription = null,
//                        tint = Color(63, 99, 203, 200)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = {
                        val email = emailFieldState.trim()

                        if (email.isBlank()) {
                            Toast.makeText(context, "Please fill in email", Toast.LENGTH_LONG)
                                .show()
                            return@Button
                        }
                        isLoading = true

                        Firebase.auth.sendPasswordResetEmail(emailFieldState)
                            .addOnCompleteListener { task ->
                                isLoading = false
                                if (task.isSuccessful) {
                                    Toast.makeText(
                                        context,
                                        "Check your email!",
                                        Toast.LENGTH_LONG
                                    )
                                        .show()
                                    navController.popBackStack()
                                } else {
                                    Toast.makeText(
                                        context,
                                        task.exception?.message ?: "An error occurred",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                    }) {
                    Text("Submit")
                }
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(64.dp)
                ) {
//                Text(text = "Back")

                }
            }
        }
    }
}
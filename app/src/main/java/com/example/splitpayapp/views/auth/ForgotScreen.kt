package com.example.splitpayapp.views.auth

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
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

@Composable
fun ForgotScreen(navController: NavController) {
    val context = LocalContext.current // For Toasts
    var emailFieldState by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    val colors = listOf(Color(173, 195, 221, 75), Color(9, 66, 133, 75)) //  Your gradient colors
    val brush = Brush.linearGradient(colors)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.askingquestion),
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .size(256.dp)
        )
        Text(
            text = "Forgot Password",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp)) {

            TextField(
                value = emailFieldState,
                onValueChange = { emailFieldState = it },
                label = { Text("Email") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            )
            {

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
                                    Toast.makeText(context, "Check your email!", Toast.LENGTH_LONG)
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
            }
        }
    }
}

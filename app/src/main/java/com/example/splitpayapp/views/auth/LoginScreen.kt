package com.example.splitpayapp.views.auth

import android.app.Activity.RESULT_OK
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.splitpayapp.R
import com.example.splitpayapp.Screens
import com.example.splitpayapp.googlesignin.GoogleAuthUiClient
import com.example.splitpayapp.graphs.Graph
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    navController: NavController,
    googleAuthUiClient: GoogleAuthUiClient
) {
    val context = LocalContext.current
    val emailFieldState = remember { mutableStateOf("") }
    val passwordFieldState = remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) } // For loading state
    var passwordVisibility by remember { mutableStateOf(false) }

    // Run when fun start(Sign In with Google)
    LaunchedEffect(key1 = Unit) {
        if(googleAuthUiClient.getSignedInUser() != null) {
            navController.navigate(Graph.MAIN_NAV)
        }
    }
    val scope = rememberCoroutineScope()
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if (result.resultCode == RESULT_OK) {
                scope.launch {
                    val signInResult =
                        googleAuthUiClient.signInWithIntent(result.data ?: return@launch)
                    signInResult.data?.run {
                        navController.navigate(Graph.MAIN_NAV) {
                            popUpTo(Screens.LoginScreen.name) { inclusive = true }
                        }
                    } ?: run {
                        Toast.makeText(context,"Something wrong with Sign In", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.chillnormblueblack),
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .size(256.dp)
        )
        Text(
            text = "Sign In",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,

            )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 32.dp, start = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            OutlinedTextField(
                value = emailFieldState.value,
                onValueChange = { emailFieldState.value = it },
                label = { Text("Email") },
                trailingIcon = {
                    Icon(
                        Icons.Outlined.Email,
                        contentDescription = null,
                        tint = Color(63, 99, 203, 200)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            OutlinedTextField(
                value = passwordFieldState.value,
                onValueChange = { passwordFieldState.value = it },
                label = { Text("Password") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                trailingIcon = {
                    IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                        Icon(
                            imageVector = if (passwordVisibility) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility,
                            contentDescription = null,
                            tint = Color(63, 99, 203, 200)
                        )
                    }
                },
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
                TextButton(
                    onClick = {
                        /*Switch*/
                        navController.navigate(Screens.ForgotScreen.name)
                    }
                ) {
                    Text("Forgot Password?")
                }

            // Sign In button
            Button(
                onClick = {
                    val email = emailFieldState.value.trim()
                    val password = passwordFieldState.value.trim()

                    if (email.isBlank() || password.isBlank()) {
                        Toast.makeText(
                            context,
                            "Please fill in both email and password",
                            Toast.LENGTH_LONG
                        ).show()
                        return@Button
                    }

                    isLoading = true // Show loading State

                    Firebase.auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            isLoading = false
                            if (task.isSuccessful) {
                                navController.navigate(Graph.MAIN_NAV) {
                                    popUpTo(Screens.LoginScreen.name) {
                                        inclusive = true
                                    } // Clear auth from backstack
                                }
                            } else {
                                val errorMessage = task.exception?.message ?: "Sign-in failed"
                                Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
                            }
                        }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text("Continue")
            }

            TextButton(onClick = {
                scope.launch {
                    val intentSender = googleAuthUiClient.signIn()
                    intentSender?.let {
                        launcher.launch(IntentSenderRequest.Builder(it).build())
                    }
                }
            }, modifier = Modifier.padding(16.dp)) {
                Text(text = "Sign In with ")
                Image(
                    painter = painterResource(id = R.drawable.googlelogo),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }

            TextButton(
                onClick = {
                    /*Switch*/
                    navController.navigate(Screens.RegisterScreen.name)

                }
            ) { // Switch mode
                Text("Create an account")
            }
        }


    }
}
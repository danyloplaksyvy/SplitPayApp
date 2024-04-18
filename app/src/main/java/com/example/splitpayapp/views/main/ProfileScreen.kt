package com.example.splitpayapp.views.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.GraphicLayerInfo
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.splitpayapp.googlesignin.GoogleAuthUiClient
import com.example.splitpayapp.googlesignin.UserData
import com.example.splitpayapp.graphs.Graph

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    googleAuthUiClient: GoogleAuthUiClient,
    onSignOut: () -> Unit
) {

    var userData by remember { mutableStateOf<UserData?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) { // Запрос данных при старте
        isLoading = true
        val cachedUserData = googleAuthUiClient.getSignedInUser()
        userData = cachedUserData
        isLoading = false
    }

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                text = "Profile",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        })
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (userData?.profilePictureUrl != null) {
                AsyncImage(
                    model = userData?.profilePictureUrl,
                    contentDescription = "Profile picture",
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            if (userData?.username != null) {
                Text(
                    text = userData?.username.toString(),
                    textAlign = TextAlign.Center,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(16.dp))
            }


            Button(onClick = onSignOut) {
                Text(text = "Sign out")
            }
        }
    }
}
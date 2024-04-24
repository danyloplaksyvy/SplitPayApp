package com.example.splitpayapp.views.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupsScreen() {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            Text(
                text = "Friends"
            )
        }
//            , actions = {
//            TextButton(onClick = { }) {
//                Text(text = "Add Group")
////                Icon(imageVector = Icons.Default.PersonAdd, contentDescription = "Add a friend")
//            }
//        }
        )
    }, floatingActionButton = {
        FloatingActionButton(
            onClick = { },
            containerColor = Color(63, 99, 203),
            shape = CircleShape
        ) {
            Icon(Icons.Default.Add, "")
        }
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "This is Groups Screen")
        }
    }
}
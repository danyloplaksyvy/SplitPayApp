package com.example.splitpayapp.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ArticleScreen() {
    Scaffold(topBar = {
        TopBar(screenName = "Articles")
    }, bottomBar = {
        BottomAppBar {
            IconsBottomBar(iconTypeGroups = Icons.Filled.Build)
        }
    }) { innerPadding ->
        Column {
            Text(text = "Text", modifier = Modifier.padding(innerPadding))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleScreenPreview() {
    ArticleScreen()
}
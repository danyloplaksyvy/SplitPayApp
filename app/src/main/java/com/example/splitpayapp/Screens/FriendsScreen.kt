package com.example.splitpayapp.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FriendsScreen() {

    Scaffold(topBar = {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer, titleContentColor = MaterialTheme.colorScheme.primary),
            title = {
                Text(text = "Friends")
            },
            actions = {
                Button(onClick = { /*TODO*/ }) {
                    Text("Add a new friend")
                }
            }
        )
    }, bottomBar = {
        BottomAppBar {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val modifierIconButton = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(8.dp)

                FloatingActionButton(onClick = { /*TODO*/ }, modifier = modifierIconButton) {
                    Icon(Icons.Default.Person, "Friends", modifier = Modifier.fillMaxSize())
                }
                FloatingActionButton(onClick = { /*TODO*/ }, modifier = modifierIconButton) {
                    Icon(Icons.Default.Person, "Groups", modifier = Modifier.fillMaxSize())
                }
                FloatingActionButton(onClick = { /*TODO*/ }, modifier = modifierIconButton) {
                    Icon(Icons.Default.Add, "Add Action", modifier = Modifier.fillMaxSize())
                }
                FloatingActionButton(onClick = { /*TODO*/ }, modifier = modifierIconButton) {
                    Icon(Icons.Default.MailOutline, "Activity", modifier = Modifier.fillMaxSize())
                }
                FloatingActionButton(onClick = { /*TODO*/ }, modifier = modifierIconButton) {
                    Icon(Icons.Default.Build, "Article", modifier = Modifier.fillMaxSize())
                }
            }
        }
    }) { innerPadding ->
        Column {
            Text(text = "Text", modifier = Modifier.padding(innerPadding))
        }
    }

}

@Preview(showBackground = true)
@Composable
fun FriendsScreenPreview() {
    FriendsScreen()
}
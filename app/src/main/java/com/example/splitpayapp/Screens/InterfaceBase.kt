package com.example.splitpayapp.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(screenName: String) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        title = {
            Text(text = "$screenName")
        },
        actions = {
            when (screenName) {
                "Friends" -> {
                    TextButton(onClick = { /*TODO*/ }) {
                        Text("Add a new friend")
                    }
                }
                "Groups" -> {
                    TextButton(onClick = { /*TODO*/ }) {
                        Text("Create a group")
                    }
                }
                "Articles" -> {
                    TextButton(onClick = { /*TODO*/ }) {
                        Text("Create an article")
                    }
                }
                else -> {}
            }
        }
    )
}

@Composable
fun IconsBottomBar(
    iconTypeFriends: ImageVector = Icons.Outlined.Person,
    iconTypeGroups: ImageVector = Icons.Outlined.Person,
    iconTypeRecentActivity: ImageVector = Icons.Outlined.MailOutline,
    iconTypeArticle: ImageVector = Icons.Outlined.Build,
    navigationToFriendsScreen: () -> Unit,
    navigationToGroupsScreen: () -> Unit,
    navigationToAddExpenseScreen: () -> Unit,
    navigationToRecentActivityScreen: () -> Unit,
    navigationToArticleScreen: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val modifierIconButton = Modifier
            .fillMaxSize()
            .weight(1f)
        TextButton(onClick = { navigationToFriendsScreen() }, modifier = modifierIconButton ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(iconTypeFriends, "Friends")
                Text(text = "Friends")
            }
        }
        TextButton(onClick = { navigationToGroupsScreen() }, modifier = modifierIconButton) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(iconTypeGroups, "Groups")
                Text(text = "Groups")
            }
        }
        TextButton(onClick = { navigationToAddExpenseScreen() }, modifier = modifierIconButton) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(Icons.Filled.AddCircle, "Add", modifierIconButton.fillMaxSize())
            }
        }
        TextButton(onClick = { navigationToRecentActivityScreen() }, modifier = modifierIconButton) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(iconTypeRecentActivity, "Recent")
                Text(text = "Recent")
            }
        }
        TextButton(onClick = { navigationToArticleScreen() }, modifier = modifierIconButton) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(iconTypeArticle, "Build")
                Text(text = "Build")
            }
        }
    }
}
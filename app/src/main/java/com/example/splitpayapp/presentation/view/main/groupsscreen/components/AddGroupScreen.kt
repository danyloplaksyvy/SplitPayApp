package com.example.splitpayapp.presentation.view.main.groupsscreen.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AirplanemodeActive
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.HeartBroken
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Tour
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.splitpayapp.presentation.view.main.friendsscreen.components.Friend
import com.example.splitpayapp.presentation.view.main.friendsscreen.friendsviewmodel.FriendsViewModel
import com.example.splitpayapp.presentation.view.main.groupsscreen.groupsviewmodel.GroupsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddGroupScreen(
    onCancelClick: () -> Unit,
    groupsViewModel: GroupsViewModel,
    onAddGroupClick: () -> Unit
) {

    val categoryItem = listOf(
        CategoryGroupItem.Any,
        CategoryGroupItem.Home,
        CategoryGroupItem.Work,
        CategoryGroupItem.Health,
        CategoryGroupItem.Travel,
        CategoryGroupItem.Bills,
        CategoryGroupItem.Education
    )

    val nameFieldState = remember { mutableStateOf("") }

    val context = LocalContext.current

    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = { Text(text = "Add Group") }, actions = {
            TextButton(
                onClick = {
                    if (nameFieldState.value.isNotBlank()) {
                        val newGroup = Group(
                            id = 0,
                            name = nameFieldState.value
                        )
                        groupsViewModel.addGroup(newGroup = newGroup)
                        onAddGroupClick()
                        nameFieldState.value = ""
                    } else {
                        Toast.makeText(context, "Enter name", Toast.LENGTH_LONG).show()
                    }
                }) {
                Text(text = "Add")
            }
        }, navigationIcon = {
            TextButton(onClick = { onCancelClick() }) {
                Text(text = "Cancel")
            }
        })
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                // Name
                OutlinedTextField(
                    value = nameFieldState.value,
                    onValueChange = { nameFieldState.value = it },
                    label = { Text("Group Name") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    trailingIcon = {
                        Icon(
                            Icons.Outlined.Person,
                            contentDescription = null,
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            // Category
            Spacer(modifier = Modifier.padding(16.dp))
            CategoryLazyRow(categoryItem = categoryItem)
            // Members
            Spacer(modifier = Modifier.padding(16.dp))
            Text(
                text = "Members: ",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                textAlign = TextAlign.Start
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .clickable { }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 1f))
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Groups,
                        contentDescription = "Members",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(36.dp)
                    )
                    Text(
                        text = "Add Members...",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun AddGroupScreenPreview() {
    val groupsViewModel = GroupsViewModel()

    AddGroupScreen(
        onCancelClick = { /* Do nothing for preview */ },
        groupsViewModel = groupsViewModel,
        onAddGroupClick = { /* Do nothing for preview */ }
    )
}
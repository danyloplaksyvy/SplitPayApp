package com.example.splitpayapp.presentation.view.main.groupsscreen.components

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
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

    val sItems by remember { mutableStateOf(listOf<Friend>()) }
    val nameFieldState = remember { mutableStateOf("") }

    val context = LocalContext.current

    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = { Text(text = "Add Group") }, actions = {
            TextButton(
                onClick = {
                    if (nameFieldState.value.isNotBlank()) {
                        val newGroup = Group(
                            id = sItems.size + 1,
                            name = nameFieldState.value
                        )
                        groupsViewModel.addGroup(newGroup = newGroup)
                        onAddGroupClick()
//                        sItems = sItems + newFriend
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
                    .padding(8.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Groups, contentDescription = "Photo")
                // Name
                OutlinedTextField(
                    value = nameFieldState.value,
                    onValueChange = { nameFieldState.value = it },
                    label = { Text("Full Name") },
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
                        .padding(8.dp)
                        .fillMaxWidth()
                )
            }
        }
    }

}
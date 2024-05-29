package com.example.splitpayapp.presentation.view.main.addexpensescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.splitpayapp.presentation.view.main.addexpensescreen.components.ExpenseDetails
import com.example.splitpayapp.presentation.view.main.friendsscreen.components.Friend
import com.example.splitpayapp.presentation.view.main.friendsscreen.friendsviewmodel.FriendsViewModel
import com.example.splitpayapp.presentation.view.main.groupsscreen.components.AddMemberGroupDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExpenseScreen(friendsViewModel: FriendsViewModel, onCreateExpense: () -> Unit) {

    val friends by friendsViewModel.friends.collectAsState()

    var showDialogFriends by remember { mutableStateOf(false) }
    var showDialogGroups by remember { mutableStateOf(false) }
    var selectedFriendsForGroup by remember { mutableStateOf<List<Friend>>(emptyList()) }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            Text(text = "Expense")
        }, colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.background))
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ExpenseDetails(onClickFriends = { showDialogForFriends ->
                showDialogFriends = showDialogForFriends
            }, onClickGroups = { showDialogForGroups ->
                showDialogGroups = showDialogForGroups
            })
            Button(onClick = { onCreateExpense()}, ) {
                Text(text = "Add Expense")
            }
        }
    }

    // Alert Dialog for Friends
    if (showDialogFriends) {
        AddMemberGroupDialog(
            friends = friends,
            onDismiss = { showDialogFriends = false },
            onConfirm = { selectedFriends ->
                selectedFriendsForGroup = selectedFriends
                showDialogFriends = false
            })
    }
    if (showDialogGroups) {
        AddMemberGroupDialog(
            friends = friends,
            onDismiss = { showDialogGroups = false },
            onConfirm = { selectedFriends ->
                selectedFriendsForGroup = selectedFriends
                showDialogGroups = false
            })
    }
}
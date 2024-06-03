package com.example.splitpayapp.presentation.view.main.addexpensescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.splitpayapp.presentation.view.main.addexpensescreen.components.AddGroupMemberDialog
import com.example.splitpayapp.presentation.view.main.addexpensescreen.components.Expense
import com.example.splitpayapp.presentation.view.main.addexpensescreen.components.ExpenseDetails
import com.example.splitpayapp.presentation.view.main.addexpensescreen.expenseviewmodel.ExpenseViewModel
import com.example.splitpayapp.presentation.view.main.friendsscreen.components.Friend
import com.example.splitpayapp.presentation.view.main.friendsscreen.friendsviewmodel.FriendsViewModel
import com.example.splitpayapp.presentation.view.main.groupsscreen.components.AddFriendMemberDialog
import com.example.splitpayapp.presentation.view.main.groupsscreen.components.Group
import com.example.splitpayapp.presentation.view.main.groupsscreen.groupsviewmodel.GroupsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExpenseScreen(
    friendsViewModel: FriendsViewModel,
    groupsViewModel: GroupsViewModel,
    expenseViewModel: ExpenseViewModel = hiltViewModel(),
    onCreateExpense: () -> Unit
) {

    val friends by friendsViewModel.friends.collectAsState()
    val groups by groupsViewModel.groups.collectAsState()

    var showDialogFriends by remember { mutableStateOf(false) }
    var showDialogGroups by remember { mutableStateOf(false) }
    var selectedFriendsForExpense by remember { mutableStateOf<List<Friend>>(emptyList()) }
    var selectedGroupsForExpense by remember { mutableStateOf<List<Group>>(emptyList()) }

    var descriptionState by remember { mutableStateOf("") }
    var amountState by remember { mutableStateOf("") }
    var categoryState = remember { mutableStateOf("Any") }

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
            verticalArrangement = Arrangement.Top
        ) {
            ExpenseDetails(onClickFriends = { showDialogForFriends ->
                showDialogFriends = showDialogForFriends
            }, onClickGroups = { showDialogForGroups ->
                showDialogGroups = showDialogForGroups
            })
            Spacer(modifier = Modifier.padding(16.dp))
            Button(onClick = {
                // TODO -> Fix add expense, add error handling
//                // Create Expense object
//                val expense = Expense(
//                    name = descriptionState, // Use your descriptionState from ExpenseDetails
//                    sum = amountState.toFloat(), // Convert to float
//                    category = categoryState.value,
//                    typeExpense = "Group", // If you have types
//                    groupId = selectedGroupsForExpense[0].id, // Get group ID
//                    participants = selectedFriendsForExpense.map { it.id }, // Get selected friends IDs
//                    // amounts = ... (if you have unequal splits)
//                )
//
//                // Add to Firestore using ViewModel
//                expenseViewModel.addExpense(expense)
//
//                // Navigate, show success message
//                onCreateExpense()
            }) {
                Text(text = "Confirm")
            }
        }
    }

    // Alert Dialog for Friends
    if (showDialogFriends) {
        AddFriendMemberDialog(
            friends = friends,
            onDismiss = { showDialogFriends = false },
            onConfirm = { selectedFriends ->
                selectedFriendsForExpense = selectedFriends
                showDialogFriends = false
            })
    }
    // Alert Dialog for Groups
    if (showDialogGroups) {
        AddGroupMemberDialog(
            groups = groups,
            onDismiss = { showDialogGroups = false },
            onConfirm = { selectedGroups ->
                selectedGroupsForExpense = selectedGroups
                showDialogGroups = false
            })
    }
}
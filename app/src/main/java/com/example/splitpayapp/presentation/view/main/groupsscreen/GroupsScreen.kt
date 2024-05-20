package com.example.splitpayapp.presentation.view.main.groupsscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.TextButton
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.splitpayapp.FunctionalityAlert
import com.example.splitpayapp.presentation.view.main.components.ScrollToTopButton
import com.example.splitpayapp.presentation.view.main.friendsscreen.components.EditFriendDialog
import com.example.splitpayapp.presentation.view.main.friendsscreen.components.Friend
import com.example.splitpayapp.presentation.view.main.friendsscreen.components.FriendItem
import com.example.splitpayapp.presentation.view.main.friendsscreen.friendsviewmodel.FriendsViewModel
import com.example.splitpayapp.presentation.view.main.groupsscreen.components.EditGroupDialog
import com.example.splitpayapp.presentation.view.main.groupsscreen.components.Group
import com.example.splitpayapp.presentation.view.main.groupsscreen.components.GroupItem
import com.example.splitpayapp.presentation.view.main.groupsscreen.groupsviewmodel.GroupsViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupsScreen(onAddGroupButtonClick: () -> Unit, groupsViewModel: GroupsViewModel = hiltViewModel()) {
    val state = rememberLazyListState()
    val scope = rememberCoroutineScope()

    val groups by groupsViewModel.groups.collectAsState(emptyList())
    var showDialog by remember { mutableStateOf(false) }
    var groupToEdit by remember { mutableStateOf<Group?>(null) }

//    var functionalityNotAvailablePopup by remember { mutableStateOf(false) }
//    if (functionalityNotAvailablePopup) {
//        FunctionalityAlert { functionalityNotAvailablePopup = false }
//    }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            Text(
                text = "Groups"
            )
        }, actions = {
            TextButton(onClick = { onAddGroupButtonClick() }) {
                Text(text = "Add Group")
            }
        }
        )
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Column {
                    LazyColumn(state = state) {
                        itemsIndexed(groups, key = { _, group -> group.id }) { index, group ->
                            GroupItem(
                                group = group,
                                onUpdateGroup = {
                                    groupToEdit = group
                                    showDialog = true
                                },
                                onDeleteGroup = {
                                    groupsViewModel.removeGroup(group = group)
                                })
                        }
                    }
                }
                val showButton by remember {
                    derivedStateOf {
                        state.firstVisibleItemIndex > 0 || state.firstVisibleItemScrollOffset > 0
                    }
                }
                ScrollToTopButton(
                    enabled = showButton,
                    onClicked = {
                        scope.launch {
                            state.animateScrollToItem(0)
                        }
                    },
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
                if (showDialog && groupToEdit != null) {
                    EditGroupDialog(
                        group = groupToEdit!!,
                        onDismiss = {
                            showDialog = false
                            groupToEdit = null
                        },
                        onConfirmEdit = { updatedGroup ->
                            groupsViewModel.updateGroup(updatedGroup)
                            showDialog = false
                            groupToEdit = null
                        }
                    )
                }
            }
        }
    }
}
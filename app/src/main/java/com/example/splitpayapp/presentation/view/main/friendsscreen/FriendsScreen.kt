package com.example.splitpayapp.presentation.view.main.friendsscreen

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.splitpayapp.FunctionalityAlert
import com.example.splitpayapp.presentation.view.main.components.ScrollToTopButton
import com.example.splitpayapp.presentation.view.main.friendsscreen.components.EditFriendDialog
import com.example.splitpayapp.presentation.view.main.friendsscreen.components.Friend
import com.example.splitpayapp.presentation.view.main.friendsscreen.components.FriendItem
import com.example.splitpayapp.presentation.view.main.friendsscreen.friendsviewmodel.FriendsViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FriendsScreen(
    onAddFriendButtonClick: () -> Unit,
    friendsViewModel: FriendsViewModel = hiltViewModel()
) {

    val state = rememberLazyListState()
    val scope = rememberCoroutineScope()

    val friends by friendsViewModel.friends.collectAsState(emptyList())

    var showDialog by remember { mutableStateOf(false) }
    var friendToEdit by remember { mutableStateOf<Friend?>(null) }

    var functionalityNotAvailablePopup by remember { mutableStateOf(false) }
    if (functionalityNotAvailablePopup) {
        FunctionalityAlert { functionalityNotAvailablePopup = false }
    }


    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            Text(text = "Friends")
        }, actions = {
            TextButton(onClick = {
                onAddFriendButtonClick()
            }) {
                Text(text = "Add friend")
            }
        }, colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.background))
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
                        itemsIndexed(friends, key = { _, friend -> friend.id }) { index, friend ->
                            FriendItem(
                                friend = friend,
                                onUpdateFriend = {
                                    friendToEdit = friend
                                    showDialog = true
                                },
                                onDeleteFriend = {
                                    friendsViewModel.removeFriend(friend)
                                }
                            )
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

                if (showDialog && friendToEdit != null) {
                    EditFriendDialog(
                        friend = friendToEdit!!,
                        onDismiss = {
                            showDialog = false
                            friendToEdit = null
                        },
                        onConfirmEdit = { updatedFriend ->
                            friendsViewModel.updateFriend(updatedFriend)
                            showDialog = false
                            friendToEdit = null
                        }
                    )
                }
            }
        }
    }
}
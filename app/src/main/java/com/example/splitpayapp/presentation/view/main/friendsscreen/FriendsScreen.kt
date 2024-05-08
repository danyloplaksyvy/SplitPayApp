package com.example.splitpayapp.presentation.view.main.friendsscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.TextButton
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.splitpayapp.FunctionalityAlert
import com.example.splitpayapp.presentation.view.main.components.ScrollToTopButton
import com.example.splitpayapp.presentation.view.main.friendsscreen.components.Friend
import com.example.splitpayapp.presentation.view.main.friendsscreen.components.FriendItem
import com.example.splitpayapp.presentation.view.main.friendsscreen.friendsviewmodel.FriendsViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FriendsScreen(onAddFriendButtonClick: () -> Unit, friendsViewModel: FriendsViewModel) {
    val state = rememberLazyListState()
    val scope = rememberCoroutineScope()
    
//    val friendsViewModel: FriendsViewModel = viewModel()
    val friends = friendsViewModel.friends

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
        })
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                val items = (1..25).toList()
                LazyColumn(state = state) {
                    itemsIndexed(friends) { index, friend ->
//                            TODO -> Need to implement adding Friends
                        FriendItem(friend = friend)
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
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun FriendsScreenPreview(modifier: Modifier = Modifier) {
//    FriendsScreen()
//}
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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.splitpayapp.FunctionalityAlert
import com.example.splitpayapp.presentation.view.main.components.ScrollToTopButton
import com.example.splitpayapp.presentation.view.main.friendsscreen.components.FriendItem
import com.example.splitpayapp.presentation.view.main.friendsscreen.friendsviewmodel.FriendsViewModel
import com.example.splitpayapp.presentation.view.main.groupsscreen.components.GroupItem
import com.example.splitpayapp.presentation.view.main.groupsscreen.groupsviewmodel.GroupsViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupsScreen(onAddGroupButtonClick: () -> Unit, groupsViewModel: GroupsViewModel) {
    val state = rememberLazyListState()
    val scope = rememberCoroutineScope()

    val groups = groupsViewModel.groups

    var functionalityNotAvailablePopup by remember { mutableStateOf(false) }
    if (functionalityNotAvailablePopup) {
        FunctionalityAlert { functionalityNotAvailablePopup = false }
    }

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
                val items = (1..25).toList()
                LazyColumn(state = state) {
                    itemsIndexed(groups) { index, group ->
//                            TODO -> Need to implement adding Friends
                        GroupItem(group = group)
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
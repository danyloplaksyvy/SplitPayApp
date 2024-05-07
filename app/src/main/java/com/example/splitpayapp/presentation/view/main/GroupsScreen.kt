package com.example.splitpayapp.presentation.view.main

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
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupsScreen() {
    val state = rememberLazyListState()
    val scope = rememberCoroutineScope()

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
            TextButton(onClick = { functionalityNotAvailablePopup = true }) {
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
            Box {
                val items = (1..25).toList()
                LazyColumn(state = state) {
                    itemsIndexed(items) { index, item ->
                        Text("Item at index $index: $item", Modifier.padding(16.dp))
                    }
                }

                val showButton by remember {
                    derivedStateOf {
                        state.firstVisibleItemIndex > 0 || state.firstVisibleItemScrollOffset > 0
                    }
                }
                ScrollToTopButton(
                    // Only show if the scroller is not at the bottom
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
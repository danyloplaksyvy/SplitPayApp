package com.example.splitpayapp.presentation.view.main.articlesscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.splitpayapp.presentation.googlesignin.GoogleAuthUiClient
import com.example.splitpayapp.presentation.googlesignin.UserData
import com.example.splitpayapp.presentation.view.main.articlesscreen.components.ArticleItem
import com.example.splitpayapp.presentation.view.main.articlesscreen.components.ArticlesDataItem
import com.example.splitpayapp.presentation.view.main.components.ScrollToTopButton
import com.example.splitpayapp.presentation.view.main.recentactivityscreen.components.RecentActivityItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticlesScreen(
    googleAuthUiClient: GoogleAuthUiClient,
    onSignOut: () -> Unit,
    onAddArticleButtonClicked: () -> Unit
) {
    val articlesItem = listOf(
        ArticlesDataItem.first,
        ArticlesDataItem.second
    )

    val state = rememberLazyListState()
    val scope = rememberCoroutineScope()

    var userData by remember { mutableStateOf<UserData?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) { // Запрос данных при старте
        isLoading = true
        val cachedUserData = googleAuthUiClient.getSignedInUser()
        userData = cachedUserData
        isLoading = false
    }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Articles",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.background),
            navigationIcon = {
                IconButton(onClick = { onSignOut() }) {
                    Icon(
                        Icons.AutoMirrored.Filled.Logout,
                        contentDescription = "LogOut",
                        tint = MaterialTheme.colorScheme.tertiary
                    )
                }
            },
            actions = {
                androidx.compose.material.TextButton(onClick = { onAddArticleButtonClicked() }) {
                    Text(text = "Add Article")
                }
            })
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box {
                LazyColumn(state = state) {
                    itemsIndexed(articlesItem) { index, item ->
                        ArticleItem(articlesDataItem = item)
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
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(innerPadding),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            if (userData?.profilePictureUrl != null) {
//                AsyncImage(
//                    model = userData?.profilePictureUrl,
//                    contentDescription = "Profile picture",
//                    modifier = Modifier
//                        .size(150.dp)
//                        .clip(CircleShape),
//                    contentScale = ContentScale.Crop
//                )
//                Spacer(modifier = Modifier.height(16.dp))
//            }
//            if (userData?.username != null) {
//                Text(
//                    text = userData?.username.toString(),
//                    textAlign = TextAlign.Center,
//                    fontSize = 36.sp,
//                    fontWeight = FontWeight.SemiBold
//                )
//                Spacer(modifier = Modifier.height(16.dp))
//            }
//
//
//            Button(onClick = onSignOut) {
//                Text(
//                    text = "Sign out",
//                    style = MaterialTheme.typography.bodyLarge,
//                    color = MaterialTheme.colorScheme.onPrimary
//                )
//            }
//        }
    }
}
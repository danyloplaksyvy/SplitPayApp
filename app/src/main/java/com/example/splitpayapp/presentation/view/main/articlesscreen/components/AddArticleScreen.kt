package com.example.splitpayapp.presentation.view.main.articlesscreen.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.automirrored.outlined.Article
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.splitpayapp.presentation.view.main.addexpensescreen.components.AddMembersButton
import com.example.splitpayapp.presentation.view.main.articlesscreen.articleviewmodel.ArticleViewModel
import com.example.splitpayapp.presentation.view.main.friendsscreen.components.Friend
import com.example.splitpayapp.presentation.view.main.groupsscreen.components.CategoryLazyRow
import com.example.splitpayapp.presentation.view.main.groupsscreen.components.Group

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddArticleScreen(
    onCancelClick: () -> Unit, onAddArticleClick: () -> Unit, articleViewModel: ArticleViewModel
) {

    val nameFieldState = remember { mutableStateOf("") }
    val categoryState = remember { mutableStateOf("Any") }
    val selectedFriendsForGroup = remember { mutableStateOf<List<Friend>>(emptyList()) }

    var showDialog by remember { mutableStateOf(false) } // State for the Alert Dialog
    var currentGroup by remember { mutableStateOf<Group?>(null) }
    val articles by articleViewModel.articles.collectAsState()


    val context = LocalContext.current

    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = { Text(text = "Add Article") }, actions = {
            TextButton(onClick = {
                if (nameFieldState.value.isNotBlank()) {

                    onAddArticleClick()
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
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Name
            OutlinedTextField(
                value = nameFieldState.value,
                onValueChange = { nameFieldState.value = it },
                label = { Text("Article Name") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text, imeAction = ImeAction.Done
                ),
                trailingIcon = {
                    Icon(
                        Icons.AutoMirrored.Outlined.Article,
                        contentDescription = null,
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            Spacer(modifier = Modifier.size(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Start", modifier = Modifier.padding(8.dp))
                    Icon(Icons.Default.CalendarMonth, "Calendar Start", modifier = Modifier.padding(8.dp))
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Finish", modifier = Modifier.padding(8.dp))
                    Icon(Icons.Default.CalendarMonth, "Calendar End", modifier = Modifier.padding(8.dp))
                }
            }
            Spacer(modifier = Modifier.size(16.dp))
            CategoryLazyRow(categoryName = categoryState)
            AddMembersButton(
                onClickFriends = { },
                onClickGroups = { })
        }
    }
}
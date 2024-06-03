package com.example.splitpayapp.presentation.view.main.groupsscreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.splitpayapp.presentation.view.main.friendsscreen.components.Friend

@Composable
fun AddFriendMemberDialog(
    friends: List<Friend>,
    onDismiss: () -> Unit,
    onConfirm: (List<Friend>) -> Unit
) {
    var selectedFriends by remember { mutableStateOf<List<Friend>>(emptyList()) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Select Friends") },
        text = {
            LazyColumn {
                items(friends) { friend ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .clickable {
                                // Toggle friend selection
                                selectedFriends = if (friend in selectedFriends) {
                                    selectedFriends - friend
                                } else {
                                    selectedFriends + friend
                                }
                            }
                    ) {
                        Checkbox(
                            checked = friend in selectedFriends,
                            onCheckedChange = null // Controlled by clickable modifier
                        )
                        Text(text = " ${friend.name}", style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }
        },
        confirmButton = {
            Button(onClick = {
                onConfirm(selectedFriends)
            }) {
                Text("Add")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
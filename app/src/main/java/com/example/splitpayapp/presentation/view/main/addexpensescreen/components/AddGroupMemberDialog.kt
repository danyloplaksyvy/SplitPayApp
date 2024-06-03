package com.example.splitpayapp.presentation.view.main.addexpensescreen.components

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
import com.example.splitpayapp.presentation.view.main.groupsscreen.components.Group

@Composable
fun AddGroupMemberDialog(
    groups: List<Group>,
    onDismiss: () -> Unit,
    onConfirm: (List<Group>) -> Unit
) {
    var selectedGroups by remember { mutableStateOf<List<Group>>(emptyList()) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Select Groups") },
        text = {
            LazyColumn {
                items(groups) { group ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .clickable {
                                // Toggle group selection
                                selectedGroups = if (group in selectedGroups) {
                                    selectedGroups - group
                                } else {
                                    selectedGroups + group
                                }
                            }
                    ) {
                        Checkbox(
                            checked = group in selectedGroups,
                            onCheckedChange = null // Controlled by clickable modifier
                        )
                        Text(text = " ${group.name}", style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }
        },
        confirmButton = {
            Button(onClick = {
                onConfirm(selectedGroups)
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
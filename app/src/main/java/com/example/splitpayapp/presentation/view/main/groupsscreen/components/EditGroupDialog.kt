package com.example.splitpayapp.presentation.view.main.groupsscreen.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextAlign
import com.example.splitpayapp.presentation.view.main.friendsscreen.components.Friend

@Composable
fun EditGroupDialog(group: Group, onDismiss: () -> Unit, onConfirmEdit: (Group) -> Unit) {
    var newName by remember { mutableStateOf(group.name) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edit Friend Name", textAlign = TextAlign.Center) },
        text = {
            OutlinedTextField(
                value = newName,
                onValueChange = { newName = it },
                label = { Text("New Name") }
            )
        },
        confirmButton = {
            Button(onClick = {
                if (newName.isNotBlank()) {
                    onConfirmEdit(group.copy(name = newName)) // Update friend with new name
                    onDismiss()
                }
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
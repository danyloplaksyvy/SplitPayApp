package com.example.splitpayapp.presentation.view.main.friendsscreen.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign

@Composable
fun EditFriendDialog(friend: Friend, onDismiss: () -> Unit, onConfirmEdit: (Friend) -> Unit) {
    var newName by remember { mutableStateOf(friend.name) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edit Friend Name", textAlign = TextAlign.Center) },
        text = {
            OutlinedTextField(
                value = newName,
                onValueChange = { newName = it },
                label = { Text("New Name") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Done
                )
            )
        },
        confirmButton = {
            Button(onClick = {
                if (newName.isNotBlank()) {
                    onConfirmEdit(friend.copy(name = newName)) // Update friend with new name
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
package com.example.splitpayapp.presentation.view.main.groupsscreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.splitpayapp.presentation.view.main.friendsscreen.components.Friend

@Composable
fun EditGroupDialog(group: Group, onDismiss: () -> Unit, onConfirmEdit: (Group) -> Unit) {
    var newName by remember { mutableStateOf(group.name) }
    var newCategory = remember { mutableStateOf(group.category) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edit Group", textAlign = TextAlign.Center) },
        text = {
            Column(modifier = Modifier.wrapContentSize()) {

                OutlinedTextField(
                    value = newName,
                    onValueChange = { newName = it },
                    label = { Text("New Name") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Done
                    ), modifier = Modifier.padding(16.dp)
                )
                CategoryLazyRow(categoryName = newCategory)
            }
        },
        confirmButton = {
            Button(onClick = {
                if (newName.isNotBlank()) {
                    onConfirmEdit(group.copy(name = newName, category = newCategory.value)) // Update friend with new name
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
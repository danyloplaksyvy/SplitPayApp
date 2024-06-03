package com.example.splitpayapp.presentation.view.main.addexpensescreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DriveFileRenameOutline
import androidx.compose.material.icons.filled.Money
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.splitpayapp.presentation.view.main.groupsscreen.components.CategoryLazyRow

@Composable
fun ExpenseDetails(onClickFriends: (Boolean) -> Unit, onClickGroups: (Boolean) -> Unit) {

    var descriptionState by remember { mutableStateOf("") }
    var amountState by remember { mutableStateOf("") }
    val categoryState = remember { mutableStateOf("Any") }
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Description
        OutlinedTextField(
            value = descriptionState,
            onValueChange = { descriptionState = it },
            label = { Text(text = "Description") },
            leadingIcon = {
                Icon(
                    Icons.Default.DriveFileRenameOutline,
                    contentDescription = "Description"
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
        // Amount
        OutlinedTextField(
            value = amountState,
            onValueChange = { amountState = it },
            label = { Text(text = "Amount") },
            leadingIcon = { Icon(Icons.Default.Money, contentDescription = "Amount") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Done
            ),
            placeholder = {
                Text(text = "0.00")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
        Spacer(modifier = Modifier.padding(8.dp))
        CategoryLazyRow(categoryName = categoryState)
        AddMembersButton(
            onClickFriends = { onClickFriends(true) },
            onClickGroups = { onClickGroups(true) })
    }
}
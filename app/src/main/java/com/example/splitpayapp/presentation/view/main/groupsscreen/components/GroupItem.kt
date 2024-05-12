package com.example.splitpayapp.presentation.view.main.groupsscreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.splitpayapp.presentation.view.main.friendsscreen.components.Friend

@Composable
fun GroupItem(group: Group) {
    Card(elevation = 10.dp, shape = RoundedCornerShape(10.dp), modifier = Modifier.padding(8.dp)) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .padding(16.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.Groups,
                    contentDescription = "Photo",
                    Modifier.padding(horizontal = 8.dp)
                )
                Text(text = group.name, style = MaterialTheme.typography.bodyLarge)
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    IconButton(onClick = { /*TODO*/ }) {

                        Icon(
                            Icons.Default.Edit,
                            contentDescription = "Edit",
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            Icons.Default.Delete,
                            contentDescription = "Delete",
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GroupItemPreview() {
    GroupItem(group = group1)
}

val group1 = Group(
    1,"John"
)

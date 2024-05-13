package com.example.splitpayapp.presentation.view.main.friendsscreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun FriendItem(friend: Friend, onUpdateFriend: () -> Unit, onDeleteFriend: (Friend) -> Unit) {

    if (friend.isEditing) {
        var newName by remember { mutableStateOf(friend.name) }
    }


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
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current).data("${friend.image}")
                        .crossfade(true).build(),
                    contentDescription = "Photo",
                    modifier = Modifier
                        .size(48.dp)
                        .padding(8.dp)
                        .clip(
                            CircleShape
                        )
                )
//                Icon(
//                    Icons.Default.Person,
//                    contentDescription = "Photo",
//                    Modifier.padding(horizontal = 8.dp)
//                )
                Text(text = friend.name, style = MaterialTheme.typography.bodyLarge)
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    IconButton(onClick = { /*TODO*/ }) {
                        onUpdateFriend()
                        Icon(
                            Icons.Default.Edit,
                            contentDescription = "Edit",
                        )
                    }
                    IconButton(onClick = {
                        onDeleteFriend(friend)
                    }) {
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

//@Preview(showBackground = true)
//@Composable
//fun FriendItemPreview() {
//    FriendItem(friend = friend1)
//}

val friend1 = Friend(
    1, "John"
)



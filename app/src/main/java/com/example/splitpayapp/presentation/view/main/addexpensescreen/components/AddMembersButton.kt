package com.example.splitpayapp.presentation.view.main.addexpensescreen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun AddMembersButton(onClickFriends: (Boolean) -> Unit, onClickGroups: (Boolean) -> Unit) {

    Spacer(modifier = Modifier.padding(16.dp))
    Text(
        text = "Members: ",
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        textAlign = TextAlign.Start
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(15.dp))
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 1f))
                .padding(16.dp), horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = { onClickFriends(true) },
                colors = ButtonDefaults.buttonColors(
                    MaterialTheme.colorScheme.onPrimary.copy(
                        alpha = 0.2f
                    )
                ),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.onPrimary),
            ) {
                Text(
                    text = "Add Friends",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                    maxLines = 1
                )
            }
            Button(
                onClick = { onClickGroups(true) },
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.2f)),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.onPrimary),
            ) {
                Text(
                    text = "Add Groups",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                    maxLines = 1
                )
            }
        }
    }
}


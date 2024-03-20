package com.example.splitpayapp.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RecentActivityScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        Text(
            text = "Main Screen",
            modifier = Modifier.padding(8.dp),
            fontSize = 32.sp,
            fontStyle = FontStyle.Italic
        )
        Spacer(modifier = Modifier.height(25.dp))
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .wrapContentWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(3) { index ->
                Text(text = "Lazy Message $index", modifier = Modifier.padding(12.dp))
            }
        }
        Box(
            modifier = Modifier.run {
                fillMaxWidth()
                    .height(1.dp)
                    .background(Color(87, 71, 71, 255))
            }
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val modifierIconButton = Modifier
                .fillMaxHeight()
                .weight(1f)
                .padding(8.dp)

            IconButton(onClick = { /*TODO*/ }, modifier = modifierIconButton) {
                Icon(Icons.Default.Person, "Friends", modifier = Modifier.fillMaxSize())
            }
            IconButton(onClick = { /*TODO*/ }, modifier = modifierIconButton) {
                Icon(Icons.Default.Person, "Groups", modifier = Modifier.fillMaxSize())
            }
            Button(onClick = { /*TODO*/ }, modifier = modifierIconButton) {
                Icon(Icons.Default.Add, "Add Action", modifier = Modifier.fillMaxSize())
            }
            IconButton(onClick = { /*TODO*/ }, modifier = modifierIconButton) {
                Icon(Icons.Default.MailOutline, "Activity", modifier = Modifier.fillMaxSize())
            }
            IconButton(onClick = { /*TODO*/ }, modifier = modifierIconButton) {
                Icon(Icons.Default.Build, "Article", modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecentActivityPreview() {
    RecentActivityScreen()
}
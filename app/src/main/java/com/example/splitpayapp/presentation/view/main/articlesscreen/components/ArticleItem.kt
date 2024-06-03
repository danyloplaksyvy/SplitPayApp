package com.example.splitpayapp.presentation.view.main.articlesscreen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.splitpayapp.presentation.view.main.recentactivityscreen.components.RecentActivityDataItem

@Composable
fun ArticleItem(articlesDataItem: ArticlesDataItem) {
    var visible by remember { mutableStateOf(true) }

    AnimatedVisibility(
        visible = visible,
        enter = expandVertically(),
        exit = shrinkVertically(animationSpec = tween(durationMillis = 500))
    ) {
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .padding(8.dp)
                .clickable { }, // TODO -> Need to clip
            elevation = 10.dp,
        ) {
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
                    Icon(articlesDataItem.categoryIcon, contentDescription = "Activity Category", modifier = Modifier.padding(end = 8.dp))
                    Column {
                        Text(text = articlesDataItem.title, style = MaterialTheme.typography.bodyMedium)
                        Text(text = articlesDataItem.action, style = MaterialTheme.typography.labelSmall)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
//                        IconButton(onClick = {
//                        }) {
//                            Icon(
//                                Icons.Default.Edit,
//                                contentDescription = "Edit",
//                            )
//                        }
                        IconButton(onClick = {
                            visible = false
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
}
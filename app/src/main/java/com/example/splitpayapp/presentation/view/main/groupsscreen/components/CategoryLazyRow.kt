package com.example.splitpayapp.presentation.view.main.groupsscreen.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CategoryLazyRow(categoryName: MutableState<String>) {
    val categoryItem = listOf(
        CategoryGroupItem.Any,
        CategoryGroupItem.Home,
        CategoryGroupItem.Work,
        CategoryGroupItem.Health,
        CategoryGroupItem.Travel,
        CategoryGroupItem.Bills,
        CategoryGroupItem.Education
    )

    var selectedItem by remember { mutableStateOf<CategoryGroupItem?>(null) }


    Text(
        text = "Category:",
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
            .clip(RoundedCornerShape(15.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 1f))
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(categoryItem) { item ->
                    val isSelected = item == selectedItem
                    val backgroundColor by animateColorAsState(
                        targetValue = if (isSelected) {
                            MaterialTheme.colorScheme.secondary.copy(alpha = 1f)
                        } else {
                            MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.2f)
                        }
                    )
                    TextButton(
                        onClick = {
                            selectedItem = item
                            categoryName.value = item.name
                        },
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onPrimary),
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = backgroundColor

                        )
                    ) {
                        Icon(
                            item.icon,
                            contentDescription = item.name,
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                        Text(text = "${item.name} ", color = MaterialTheme.colorScheme.onPrimary)
                    }
                }
            }
        }
    }
}
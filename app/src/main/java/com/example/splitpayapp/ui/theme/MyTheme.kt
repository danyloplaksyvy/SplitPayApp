package com.example.splitpayapp.ui.theme

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.lightColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun MyTheme(content: @Composable () -> Unit) {
    val colors = lightColorScheme(
        primary = Color(63, 99, 203),
        onPrimary = Color(242, 244, 252),
        secondary = Color(180, 153, 228),
        onSecondary = Color(242, 244, 252),
        tertiary = Color(151, 95, 212),
        onTertiary = Color(242, 244, 252),
//        surface = Color(63, 99, 203, 255),
//        onSurface = Color(5, 9, 21),
        background = Color(242, 244, 252, 255),
        onBackground = Color(5, 9, 21),
        outline = Color(63, 99, 203,50),



        // ... other theme colors
    )
    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}
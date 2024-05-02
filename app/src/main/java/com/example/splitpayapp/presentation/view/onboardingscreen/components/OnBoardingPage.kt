package com.example.splitpayapp.presentation.view.onboardingscreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OnBoardingPage(page: Page) {
    Column() {
        Image(
            painter = painterResource(id = page.image),
            contentDescription = "logo",
            modifier = Modifier
                .fillMaxHeight(.6f)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Text(
            text = page.title,
            fontSize = 32.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(8.dp)
        )
        Text(text = page.description)
    }
}
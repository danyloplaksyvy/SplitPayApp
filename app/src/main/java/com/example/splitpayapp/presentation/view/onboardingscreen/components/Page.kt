package com.example.splitpayapp.presentation.view.onboardingscreen.components

import androidx.annotation.DrawableRes
import com.example.splitpayapp.R

const val SAMPLE_TEXT =
    "Hello, my name is Danylo Plaksyvy. You are looking at the onboarding screen now."

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = "Onboarding Screen 1",
        description = SAMPLE_TEXT,
        image = R.drawable.logo
    ),
    Page(
        title = "Onboarding Screen 2",
        description = SAMPLE_TEXT,
        image = R.drawable.logo
    ),
    Page(
        title = "Onboarding Screen 3",
        description = SAMPLE_TEXT,
        image = R.drawable.logo
    )
)
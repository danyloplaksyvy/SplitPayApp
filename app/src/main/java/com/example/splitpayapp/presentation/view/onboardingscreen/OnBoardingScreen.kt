package com.example.splitpayapp.presentation.view.onboardingscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.splitpayapp.presentation.view.onboardingscreen.components.NextBackButton
import com.example.splitpayapp.presentation.view.onboardingscreen.components.OnBoardingPage
import com.example.splitpayapp.presentation.view.onboardingscreen.components.PageIndicator
import com.example.splitpayapp.presentation.view.onboardingscreen.components.pages
import com.example.splitpayapp.presentation.data.datastore.viewmodel.DataStoreViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(mainViewModel: DataStoreViewModel) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        val pagerState = rememberPagerState(initialPage = 0) {
            pages.size
        }
        val currentPage = pagerState.currentPage
        val scope = rememberCoroutineScope()

        HorizontalPager(state = pagerState) { position ->
            OnBoardingPage(page = pages[position])
        }

        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 32.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PageIndicator(pageSize = pages.size, selectedPage = currentPage)
            NextBackButton(
                currentPage = currentPage,
                onNextClick = { scope.launch { pagerState.animateScrollToPage(currentPage + 1) } },
                onBackClick = { scope.launch { pagerState.animateScrollToPage(currentPage - 1) } },
                onGetStartedClick = {
                    mainViewModel.saveAppEntry()
                })
        }
    }
}
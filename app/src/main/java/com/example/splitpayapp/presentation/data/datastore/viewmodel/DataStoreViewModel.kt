package com.example.splitpayapp.presentation.data.datastore.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.splitpayapp.presentation.data.datastore.DataStoreManager
import com.example.splitpayapp.presentation.navigation.Screens
import com.example.splitpayapp.presentation.navigation.graphs.Graph
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    var isLoading by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf(Screens.OnBoardingScreen.name)
        private set

    init {
        dataStoreManager.readAppEntry.onEach { loadOnBoardingScreen ->
            startDestination = if (loadOnBoardingScreen) {
                Screens.OnBoardingScreen.name
            } else {
                Graph.AUTH
            }
            delay(300)
            isLoading = false
        }.launchIn(viewModelScope)
    }

    fun saveAppEntry() {
        viewModelScope.launch {
            dataStoreManager.saveAppEntry()
        }
    }
}
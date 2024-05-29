package com.example.splitpayapp.presentation.view.main.friendsscreen.friendsviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.splitpayapp.presentation.view.main.friendsscreen.components.Friend
import com.example.splitpayapp.presentation.view.main.friendsscreen.friendsrepository.FriendsRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class FriendsViewModel @Inject constructor(
    private val repository: FriendsRepository
) : ViewModel() {
    private val _friends = MutableStateFlow<List<Friend>>(emptyList())
    val friends: StateFlow<List<Friend>> = _friends

    init {
        viewModelScope.launch {
            repository.getFriends(Firebase.auth.currentUser?.uid!!).collect {
                _friends.value = it
            }
        }
    }

    fun addFriend(friend: Friend) = viewModelScope.launch {
        repository.addFriend(friend)
    }

    fun removeFriend(friend: Friend) = viewModelScope.launch {
        friend.isDeleting = true
        viewModelScope.launch {
            delay(500) // Delay to match the animation duration
            repository.removeFriend(Firebase.auth.currentUser?.uid!!, friend)
        }
    }

    fun updateFriend(friend: Friend) = viewModelScope.launch {
        repository.updateFriend(Firebase.auth.currentUser?.uid!!, friend)
    }

}
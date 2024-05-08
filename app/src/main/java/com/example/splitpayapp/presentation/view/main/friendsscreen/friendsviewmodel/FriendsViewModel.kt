package com.example.splitpayapp.presentation.view.main.friendsscreen.friendsviewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.splitpayapp.presentation.view.main.friendsscreen.components.Friend

class FriendsViewModel: ViewModel() {
    private val _friends = mutableListOf<Friend>()
    val friends: List<Friend> get() = _friends.toList()

    fun addFriend(newFriend: Friend) {
        _friends.add(newFriend)
    }
}
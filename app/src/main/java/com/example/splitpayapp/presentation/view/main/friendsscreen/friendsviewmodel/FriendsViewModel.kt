package com.example.splitpayapp.presentation.view.main.friendsscreen.friendsviewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.example.splitpayapp.presentation.view.main.friendsscreen.components.Friend
import kotlin.coroutines.cancellation.CancellationException

class FriendsViewModel : ViewModel() {
    private val _friends = mutableListOf<Friend>()
    val friends: List<Friend> get() = _friends.toList()

    fun addFriend(newFriend: Friend) {
            _friends.add(newFriend)
    }

    fun updateFriendName(friend: Friend, newName: String) {
        val index = _friends.indexOf(friend)
        _friends[index] = friend.copy(name = newName, isEditing = false)
    }

    fun removeFriend(friend: Friend) {
        _friends.remove(friend)
    }
}
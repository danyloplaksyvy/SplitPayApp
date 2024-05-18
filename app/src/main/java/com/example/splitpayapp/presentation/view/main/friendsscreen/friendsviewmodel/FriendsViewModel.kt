package com.example.splitpayapp.presentation.view.main.friendsscreen.friendsviewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.splitpayapp.presentation.view.main.friendsscreen.components.Friend
import com.example.splitpayapp.presentation.view.main.friendsscreen.components.friendsrepository.FriendsRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

class FriendsViewModel @Inject constructor(
    private val repository: FriendsRepository
) : ViewModel() {
    private val _friends = mutableStateListOf<Friend>()
    val friends: List<Friend> get() = _friends.toList()

    private var friendId = 1

    fun addFriend(newFriend: Friend) = viewModelScope.launch {
//        repository
        _friends.add(newFriend.copy(id = friendId++))
    }

    fun updateFriendName(friend: Friend, newName: String) {
        val index = _friends.indexOfFirst { it.id == friend.id }
        if (index != -1) {
            _friends[index] = friend.copy(name = newName)
        }
    }

    fun removeFriend(friend: Friend) {
        friend.isDeleting = true
        viewModelScope.launch {
            delay(500) // Delay to match the animation duration
            _friends.remove(friend)
        }
    }
}
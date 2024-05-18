package com.example.splitpayapp.presentation.view.main.friendsscreen.friendsviewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.splitpayapp.presentation.view.main.friendsscreen.components.Friend
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

class FriendsViewModel (
) : ViewModel() {
    private val _friends = mutableStateListOf<Friend>()
    val friends: List<Friend> get() = _friends.toList()

    private var friendId = 1
//    private val fs = Firebase.firestore

//    suspend
    fun addFriend(friend: Friend) {
        _friends.add(friend.copy(id = friendId++))
//        val userId = com.google.firebase.Firebase.auth.currentUser?.uid ?: return
//
//        com.google.firebase.Firebase.firestore
//            .collection("users")
//            .document(userId)
//            .collection("friends")
//            .add(friend)
//            .await()
    }

    suspend fun getFriends(friend: Friend) {

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
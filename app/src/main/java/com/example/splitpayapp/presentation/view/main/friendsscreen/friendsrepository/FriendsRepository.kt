package com.example.splitpayapp.presentation.view.main.friendsscreen.friendsrepository

import com.example.splitpayapp.presentation.view.main.friendsscreen.components.Friend
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FriendsRepository @Inject constructor() {

    suspend fun addFriend(friend: Friend) {
        val userId = Firebase.auth.currentUser?.uid ?: return

        Firebase.firestore
            .collection("users")
            .document(userId)
            .collection("friends")
            .add(friend)
            .await()
    }
}
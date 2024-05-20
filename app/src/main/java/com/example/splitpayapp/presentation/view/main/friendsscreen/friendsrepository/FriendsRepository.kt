package com.example.splitpayapp.presentation.view.main.friendsscreen.friendsrepository

import com.example.splitpayapp.presentation.view.main.friendsscreen.components.Friend
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FriendsRepository @Inject constructor() {
    private val db = Firebase.firestore

//
//    suspend fun addFriend(userId: String, friend: Friend): Result<Void> = withContext(Dispatchers.IO) {
//        runCatching {
//            db.collection("users")
//                .document(userId)
//                .collection("friends")
//                .document(friend.id)
//                .set(friend)
//                .await()
//        }
//    }

    suspend fun addFriend(friend: Friend) {
        val userId = Firebase.auth.currentUser?.uid ?: return // Get user ID

        // Get a new document reference with an auto-generated ID
        val newFriendRef = db.collection("users")
            .document(userId)
            .collection("friends")
            .document() // Create a new document with an auto-generated ID

        // Set the friend data with the new ID
        newFriendRef.set(friend.copy(id = newFriendRef.id)).await()
    }

    suspend fun removeFriend(userId: String, friend: Friend) {
        Firebase.firestore.collection("users")
            .document(userId)
            .collection("friends")
            .document(friend.id) // Reference friend using ID
            .delete()
            .await()
    }

    suspend fun updateFriend(userId: String, friend: Friend) {
        Firebase.firestore.collection("users")
            .document(userId)
            .collection("friends")
            .document(friend.id) // Reference friend using ID
            .set(friend, SetOptions.merge())
            .await()
    }

fun getFriends(userId: String): Flow<List<Friend>> = callbackFlow {
    val listener = db.collection("users")
        .document(userId)
        .collection("friends")
        .addSnapshotListener { snapshot, error ->
            if (error != null) {
                close(error)
                return@addSnapshotListener
            }

            val friendsList = snapshot?.toObjects(Friend::class.java) ?: emptyList()
            trySend(friendsList).isSuccess
        }

    awaitClose { listener.remove() }
}
}
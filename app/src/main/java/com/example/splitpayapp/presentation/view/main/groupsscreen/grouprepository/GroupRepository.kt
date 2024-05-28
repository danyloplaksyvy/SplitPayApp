package com.example.splitpayapp.presentation.view.main.groupsscreen.grouprepository

import com.example.splitpayapp.presentation.view.main.friendsscreen.components.Friend
import com.example.splitpayapp.presentation.view.main.groupsscreen.components.Group
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GroupRepository @Inject constructor() {
    private val db = Firebase.firestore

    companion object {
        private const val GROUPS = "groups"
        private const val USERS = "users"
    }

    suspend fun addGroup(group: Group) {
        val userId = Firebase.auth.currentUser?.uid ?: return // Get user ID

        // Get a new document reference with an auto-generated ID
        val newGroupRef = db.collection(USERS)
            .document(userId)
            .collection(GROUPS)
            .document() // Create a new document with an auto-generated ID

        // Set the group data with the new ID
        newGroupRef.set(group.copy(id = newGroupRef.id)).await()
    }

    suspend fun removeGroup(userId: String, group: Group) {
        db.collection(USERS)
            .document(userId)
            .collection(GROUPS)
            .document(group.id)
            .delete()
            .await()
    }

    suspend fun updateGroup(userId: String, group: Group) {
        db.collection(USERS)
            .document(userId)
            .collection(GROUPS)
            .document(group.id)
            .set(group, SetOptions.merge())
            .await()
    }

    suspend fun addMembersToGroup(groupId: String, memberIds: List<String>) {
        val groupRef = db.collection(USERS)
            .document(Firebase.auth.currentUser?.uid ?: return) // Get the current user ID
            .collection(GROUPS)
            .document(groupId)

        // Update the members array to include the new member IDs
        groupRef.update("members", FieldValue.arrayUnion(*memberIds.toTypedArray()))
            .await()
    }

    fun getGroups(userId: String): Flow<List<Group>> = callbackFlow {
        val listener = db.collection(USERS)
            .document(userId)
            .collection(GROUPS)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }

                val groupsList = snapshot?.toObjects(Group::class.java) ?: emptyList()
                trySend(groupsList).isSuccess
            }

        awaitClose { listener.remove() }
    }
}
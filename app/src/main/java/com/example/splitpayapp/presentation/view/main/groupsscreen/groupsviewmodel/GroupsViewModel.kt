package com.example.splitpayapp.presentation.view.main.groupsscreen.groupsviewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.splitpayapp.presentation.view.main.friendsscreen.components.Friend
import com.example.splitpayapp.presentation.view.main.groupsscreen.components.Group

class GroupsViewModel: ViewModel() {
    private val _groups = mutableStateListOf<Group>()
    val groups: List<Group> get() = _groups.toList()

    fun addGroup(newGroup: Group) {
        _groups.add(newGroup)
    }
}
package com.example.splitpayapp.presentation.view.main.groupsscreen.groupsviewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.splitpayapp.presentation.view.main.friendsscreen.components.Friend
import com.example.splitpayapp.presentation.view.main.groupsscreen.components.Group
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GroupsViewModel: ViewModel() {
    private val _groups = mutableStateListOf<Group>()
    val groups: List<Group> get() = _groups.toList()

    private var groupId = 1

    fun addGroup(newGroup: Group) {
        _groups.add(newGroup.copy(id = groupId++))
    }


    fun updateGroupName(group: Group, newName: String) {
        val index = _groups.indexOfFirst { it.id == group.id }
        if (index != -1) {
            _groups[index] = group.copy(name = newName)
        }
    }

    fun removeGroup(group: Group) {
        group.isDeleting = true
        viewModelScope.launch {
            delay(500) // Delay to match the animation duration
            _groups.remove(group)
        }
    }
}
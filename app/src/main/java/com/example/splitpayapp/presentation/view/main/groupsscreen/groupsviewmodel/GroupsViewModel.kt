package com.example.splitpayapp.presentation.view.main.groupsscreen.groupsviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.splitpayapp.presentation.view.main.groupsscreen.components.Group
import com.example.splitpayapp.presentation.view.main.groupsscreen.grouprepository.GroupRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupsViewModel @Inject constructor(
    private val repository: GroupRepository
) : ViewModel() {
    private val _groups = MutableStateFlow<List<Group>>(emptyList())
    val groups: StateFlow<List<Group>> = _groups

    init {
        viewModelScope.launch {
            repository.getGroups(Firebase.auth.currentUser?.uid!!).collect {
                _groups.value = it
            }
        }
    }

    fun addGroup(group: Group) = viewModelScope.launch {
        repository.addGroup(group)
    }

    fun removeGroup(group: Group) = viewModelScope.launch {
        group.isDeleting = true
        viewModelScope.launch {
            delay(500) // Delay to match the animation duration
            repository.removeGroup(Firebase.auth.currentUser?.uid!!, group)
        }
    }

    fun updateGroup(group: Group) = viewModelScope.launch {
        repository.updateGroup(Firebase.auth.currentUser?.uid!!, group)
    }

}
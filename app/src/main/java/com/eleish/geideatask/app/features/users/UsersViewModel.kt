package com.eleish.geideatask.app.features.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eleish.geideatask.entities.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UsersViewModel : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>>
        get() = _users

    init {
        fetchUsers()
    }

    fun fetchUsers() {
        _loading.value = true
        viewModelScope.launch {
            delay(1000)
            _loading.postValue(false)
            _users.postValue(listOf(
                User(1, "Mohamed", "Eleish"),
                User(2, "Mohamed", "Ayman")))
        }
    }
}
package com.eleish.geideatask.app.features.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eleish.geideatask.domain.usecases.FetchUsersUseCase
import com.eleish.geideatask.domain.usecases.FetchUsersUseCaseImpl
import com.eleish.geideatask.entities.Result
import com.eleish.geideatask.entities.User
import kotlinx.coroutines.launch

class UsersViewModel(
    private val fetchUsersUseCase: FetchUsersUseCase = FetchUsersUseCaseImpl(),
) : ViewModel() {

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
            when (val result = fetchUsersUseCase.invoke()) {
                is Result.Failure -> {

                }
                is Result.Success -> {
                    _users.postValue(result.data)
                }
            }
            _loading.postValue(false)
        }
    }
}
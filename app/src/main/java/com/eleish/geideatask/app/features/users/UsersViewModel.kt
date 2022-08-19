package com.eleish.geideatask.app.features.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eleish.geideatask.app.core.BaseViewModel
import com.eleish.geideatask.domain.usecases.FetchUsersUseCase
import com.eleish.geideatask.domain.usecases.FetchUsersUseCaseImpl
import com.eleish.geideatask.entities.Result
import com.eleish.geideatask.entities.User
import kotlinx.coroutines.launch

class UsersViewModel(
    private val fetchUsersUseCase: FetchUsersUseCase = FetchUsersUseCaseImpl(),
) : BaseViewModel() {

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
                    _error.postValue(result.exception.message)
                }
                is Result.Success -> {
                    _users.postValue(result.data)
                }
            }
            _loading.postValue(false)
        }
    }
}
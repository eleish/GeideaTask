package com.eleish.geideatask.app.features.userdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.eleish.geideatask.app.core.BaseViewModel
import com.eleish.geideatask.domain.usecases.FetchUserDetailsUseCase
import com.eleish.geideatask.entities.Result
import com.eleish.geideatask.entities.User
import kotlinx.coroutines.launch

class UserDetailsViewModel(
    private val userId: Int,
    private val fetchUserDetailsUseCase: FetchUserDetailsUseCase,
) : BaseViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    var remainingTimerMillis = 5000L

    init {
        fetchUserData()
    }

    fun fetchUserData() {
        _loading.value = true
        viewModelScope.launch {
            when (val result = fetchUserDetailsUseCase.invoke(userId)) {
                is Result.Failure -> {
                    _error.postValue(result.exception.message)
                }
                is Result.Success -> {
                    _user.postValue(result.data)
                }
            }
            _loading.postValue(false)
        }
    }
}
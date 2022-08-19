package com.eleish.geideatask.app.features.userdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eleish.geideatask.domain.usecases.FetchUserDetailsUseCase
import com.eleish.geideatask.entities.Result
import com.eleish.geideatask.entities.User
import kotlinx.coroutines.launch

class UserDetailsViewModel(
    private val userId: Int,
    private val fetchUserDetailsUseCase: FetchUserDetailsUseCase,
) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    init {
        fetchUserData()
    }

    fun fetchUserData() {
        _loading.value = true
        viewModelScope.launch {
            when (val result = fetchUserDetailsUseCase.invoke(userId)) {
                is Result.Failure -> {

                }
                is Result.Success -> {
                    _user.postValue(result.data)
                }
            }
            _loading.postValue(false)
        }
    }
}
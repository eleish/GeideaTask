package com.eleish.geideatask.app.features.userdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eleish.geideatask.entities.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UserDetailsViewModel(private val userId: Int) : ViewModel() {

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
            delay(1000)
            _loading.postValue(false)
            _user.postValue(User(1,
                "Mohamed",
                "Eleish",
                "mohamed.a.eleish@gmail.com",
                "https://m.media-amazon.com/images/M/MV5BMWFmYmRiYzMtMTQ4YS00NjA5LTliYTgtMmM3OTc4OGY3MTFkXkEyXkFqcGdeQXVyODk4OTc3MTY@._V1_.jpg"))
        }
    }
}
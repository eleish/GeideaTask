package com.eleish.geideatask.app.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    protected val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    protected val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error
}
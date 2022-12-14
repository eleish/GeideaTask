package com.eleish.geideatask.app.features.userdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eleish.geideatask.domain.usecases.FetchUserDetailsUseCaseImpl

class UserDetailsViewModelFactory(private val userId: Int) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserDetailsViewModel::class.java)) {
            return UserDetailsViewModel(userId, FetchUserDetailsUseCaseImpl()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
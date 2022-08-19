package com.eleish.geideatask.domain.usecases

import com.eleish.geideatask.entities.Result
import com.eleish.geideatask.entities.User

interface FetchUsersUseCase {
    suspend fun invoke(): Result<List<User>>
}
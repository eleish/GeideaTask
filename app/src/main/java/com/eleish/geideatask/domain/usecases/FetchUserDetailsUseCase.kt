package com.eleish.geideatask.domain.usecases

import com.eleish.geideatask.entities.Result
import com.eleish.geideatask.entities.User

interface FetchUserDetailsUseCase {
    suspend fun invoke(userId: Int): Result<User>
}
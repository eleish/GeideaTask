package com.eleish.geideatask.domain.usecases

import com.eleish.geideatask.domain.repos.UserRepository
import com.eleish.geideatask.domain.repos.UserRepositoryImpl
import com.eleish.geideatask.entities.Result
import com.eleish.geideatask.entities.User

class FetchUserDetailsUseCaseImpl(
    private val repository: UserRepository = UserRepositoryImpl(),
) : FetchUserDetailsUseCase {
    override suspend fun invoke(userId: Int): Result<User> {
        return try {
            Result.Success(repository.fetchRemoteUserDetails(userId).data)
        } catch (e: Exception) {
            try {
                Result.Success(repository.fetchCachedUserDetails(userId))
            } catch (e: Exception) {
                Result.Failure(e)
            }
        }
    }
}
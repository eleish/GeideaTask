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
            val user = repository.fetchRemoteUserDetails(userId).data
            repository.cacheUsers(user)
            Result.Success(user)
        } catch (e: Exception) {
            try {
                val user = repository.fetchCachedUserDetails(userId)
                if (user != null) {
                    Result.Success(user)
                } else {
                    Result.Failure(Exception("Cache miss"))
                }

            } catch (e: Exception) {
                Result.Failure(e)
            }
        }
    }
}
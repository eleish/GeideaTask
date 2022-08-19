package com.eleish.geideatask.domain.usecases

import com.eleish.geideatask.domain.repos.UserRepository
import com.eleish.geideatask.domain.repos.UserRepositoryImpl
import com.eleish.geideatask.entities.Result
import com.eleish.geideatask.entities.User

class FetchUsersUseCaseImpl(
    private val repository: UserRepository = UserRepositoryImpl(),
) : FetchUsersUseCase {
    override suspend fun invoke(): Result<List<User>> {
        return try {
            Result.Success(repository.fetchRemoteUsers().data)
        } catch (e: Exception) {
            e.printStackTrace()
            try {
                Result.Success(repository.fetchCachedUsers())
            } catch (e: Exception) {
                Result.Failure(e)
            }
        }.run {
            if (this is Result.Success)
                Result.Success(this.data.sortedBy { it.fullName })
            else
                this
        }
    }
}
package com.eleish.geideatask.domain.repos

import com.eleish.geideatask.entities.Response
import com.eleish.geideatask.entities.User

interface UserRepository {
    suspend fun fetchRemoteUsers(): Response<List<User>>
    suspend fun fetchRemoteUserDetails(userId: Int): Response<User>
    suspend fun fetchCachedUsers(): List<User>
    suspend fun fetchCachedUserDetails(userId: Int): User?
    suspend fun cacheUsers(vararg users: User)
    suspend fun clearCachedUsers()
}
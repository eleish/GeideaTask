package com.eleish.geideatask.domain.repos

import com.eleish.geideatask.domain.core.network.OkHttpClient
import com.eleish.geideatask.domain.core.network.RetroFitClient
import com.eleish.geideatask.domain.datasources.UserRemoteService
import com.eleish.geideatask.entities.Response
import com.eleish.geideatask.entities.User

val retrofitClient by lazy {
    RetroFitClient.newInstance(
        "https://reqres.in/api/",
        OkHttpClient.newInstance()
    )
}

class UserRepositoryImpl(
    private val userRemoteService: UserRemoteService = retrofitClient.create(UserRemoteService::class.java),
) : UserRepository {

    override suspend fun fetchRemoteUsers(): Response<List<User>> {
        return userRemoteService.fetchUsers()
    }

    override suspend fun fetchRemoteUserDetails(userId: Int): Response<User> {
        return userRemoteService.fetchUserDetails(userId)
    }

    override suspend fun fetchCachedUsers(): List<User> {
        return listOf()
    }

    override suspend fun fetchCachedUserDetails(userId: Int): User {
        TODO()
    }
}
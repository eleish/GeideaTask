package com.eleish.geideatask.domain.datasources

import com.eleish.geideatask.entities.Response
import com.eleish.geideatask.entities.User
import retrofit2.http.GET
import retrofit2.http.Path

interface UserRemoteService {
    @GET("users?per_page=20")
    suspend fun fetchUsers(): Response<List<User>>

    @GET("users/{userId}")
    suspend fun fetchUserDetails(@Path("userId") userId: Int): Response<User>
}
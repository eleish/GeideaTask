package com.eleish.geideatask.domain.repos

import androidx.room.Room
import com.eleish.geideatask.domain.DomainIntegration
import com.eleish.geideatask.domain.core.network.OkHttpClient
import com.eleish.geideatask.domain.core.network.RetroFitClient
import com.eleish.geideatask.domain.datasources.AppDatabase
import com.eleish.geideatask.domain.datasources.UserDao
import com.eleish.geideatask.domain.datasources.UserRemoteService
import com.eleish.geideatask.entities.Response
import com.eleish.geideatask.entities.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private val retrofitClient by lazy {
    RetroFitClient.newInstance(
        "https://reqres.in/api/",
        OkHttpClient.newInstance()
    )
}

private val roomDatabase = Room.databaseBuilder(
    DomainIntegration.getApplicationContext(),
    AppDatabase::class.java, "geidea-database"
).build()

class UserRepositoryImpl(
    private val userRemoteService: UserRemoteService = retrofitClient.create(UserRemoteService::class.java),
    private val userDao: UserDao = roomDatabase.userDao(),
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : UserRepository {

    override suspend fun fetchRemoteUsers(): Response<List<User>> = withContext(dispatcher) {
        userRemoteService.fetchUsers()
    }

    override suspend fun fetchRemoteUserDetails(userId: Int): Response<User> =
        withContext(dispatcher) {
            userRemoteService.fetchUserDetails(userId)
        }

    override suspend fun fetchCachedUsers(): List<User> = withContext(dispatcher) {
        userDao.getAll()
    }

    override suspend fun fetchCachedUserDetails(userId: Int): User? = withContext(dispatcher) {
        userDao.get(userId).firstOrNull()
    }

    override suspend fun cacheUsers(vararg users: User) = withContext(dispatcher) {
        userDao.insertAll(*users)
    }

    override suspend fun clearCachedUsers() = withContext(dispatcher) {
        userDao.deleteAll()
    }
}
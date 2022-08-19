package com.eleish.geideatask.domain.datasources

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.eleish.geideatask.entities.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE id = (:userId) LIMIT 1")
    fun get(userId: Int): List<User>

    @Insert
    fun insertAll(vararg users: User)

    @Query("DELETE FROM user")
    fun deleteAll()
}
package com.eleish.geideatask.domain.datasources

import androidx.room.Database
import androidx.room.RoomDatabase
import com.eleish.geideatask.entities.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
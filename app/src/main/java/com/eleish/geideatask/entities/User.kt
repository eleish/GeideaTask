package com.eleish.geideatask.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class User(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @ColumnInfo(name = "first_name")
    @SerializedName("first_name")
    val firstName: String,
    @ColumnInfo(name = "last_name")
    @SerializedName("last_name")
    val lastName: String,
    @ColumnInfo(name = "email")
    @SerializedName("email")
    val email: String,
    @ColumnInfo(name = "avatar")
    @SerializedName("avatar")
    val avatar: String,
) {
    val fullName: String
        get() = "$firstName $lastName"
}

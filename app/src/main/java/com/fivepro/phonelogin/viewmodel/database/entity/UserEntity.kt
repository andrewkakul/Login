package com.fivepro.phonelogin.viewmodel.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = false) val id: Long,
    val phoneCode: String,
    val phoneNumber: String,
    val name: String,
    val secondName: String
)

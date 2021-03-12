package com.fivepro.phonelogin.viewmodel.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fivepro.phonelogin.viewmodel.database.entity.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(userEntity: UserEntity)

    @Query("SELECT * From user_table WHERE id=:userId")
    suspend fun getUser(userId: Long): UserEntity
}
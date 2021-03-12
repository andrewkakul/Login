package com.fivepro.phonelogin.viewmodel.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fivepro.phonelogin.viewmodel.database.entity.UserEntity
import com.fivepro.phonelogin.viewmodel.database.dao.UserDao

@Database(version = 1, entities = [UserEntity::class])
abstract class UsersRoomDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private var INSTANCE: UsersRoomDatabase? = null

        fun getDatabase(context: Context): UsersRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UsersRoomDatabase::class.java,
                    "users_login_db"
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
package com.fivepro.phonelogin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.fivepro.phonelogin.viewmodel.database.UsersRoomDatabase
import com.fivepro.phonelogin.viewmodel.database.entity.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    var userProfile = MutableLiveData<UserEntity>()

    fun getUserData(db: UsersRoomDatabase, userId: Long){
        viewModelScope.launch(Dispatchers.IO){
            userProfile.postValue(db.userDao().getUser(userId))
        }
    }
}
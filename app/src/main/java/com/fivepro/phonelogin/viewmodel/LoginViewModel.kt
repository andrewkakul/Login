package com.fivepro.phonelogin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.fivepro.phonelogin.model.ResponseApi
import com.fivepro.phonelogin.model.UserInfo
import com.fivepro.phonelogin.model.UserLogin
import com.fivepro.phonelogin.view.MessageInterface
import com.fivepro.phonelogin.viewmodel.database.entity.UserEntity
import com.fivepro.phonelogin.viewmodel.network.NetworkManager
import com.fivepro.phonelogin.viewmodel.database.UsersRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    var responseAnswer = MutableLiveData<Boolean>()

    private fun saveData(db: UsersRoomDatabase, userInfo: UserInfo){
        viewModelScope.launch(Dispatchers.IO){
            db.userDao().insert(UserEntity(0,
                userInfo.phoneCode,
                userInfo.phoneNumber,
                userInfo.name,
                userInfo.secondName))
        }
    }

    fun loginUser(db: UsersRoomDatabase, messageInterface: MessageInterface, userLogin: UserLogin){
        viewModelScope.launch(Dispatchers.IO) {
            val service = NetworkManager.createService()
            val call = service.loginUser(userLogin)

                call.enqueue(object : Callback<ResponseApi> {
                    override fun onResponse(
                        call: Call<ResponseApi>,
                        response: Response<ResponseApi>
                    ) {
                        try {
                            saveData(db, response.body()!!.user)
                            responseAnswer.postValue(true)
                        }
                        catch (e:Exception){
                            messageInterface.showUnsignedMessage()
                            responseAnswer.postValue(false)
                        }
                    }
                    override fun onFailure(call: Call<ResponseApi>, t: Throwable) {
                        messageInterface.showUnsignedMessage()
                    }
                })

        }
    }
}
package com.fivepro.phonelogin.viewmodel.network

import com.fivepro.phonelogin.model.ResponseApi
import com.fivepro.phonelogin.model.UserLogin
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RestApi {

    @POST("login")
    fun loginUser(@Body userData: UserLogin): Call<ResponseApi>
}
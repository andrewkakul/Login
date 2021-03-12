package com.fivepro.phonelogin.viewmodel.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkManager {

    fun createService(): RestApi {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://tips-api-staging.crio-server.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit.create(RestApi::class.java)
    }
}
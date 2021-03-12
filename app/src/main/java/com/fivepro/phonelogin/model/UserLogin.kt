package com.fivepro.phonelogin.model

import com.google.gson.annotations.SerializedName

data class UserLogin(
    @SerializedName("phone_code") val phoneCode: String,
    @SerializedName("phone_number") val phoneNumber: String,
    @SerializedName("password") val password: String
)

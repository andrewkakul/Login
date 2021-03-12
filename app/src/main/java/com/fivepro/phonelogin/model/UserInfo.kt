package com.fivepro.phonelogin.model

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("phone_code") val phoneCode: String,
    @SerializedName("phone_number") val phoneNumber: String,
    @SerializedName("name") val name: String,
    @SerializedName("second_name") val secondName: String
)

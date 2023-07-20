package com.cych.cychzenbo.model.JSONData.LoginDS

import com.google.gson.annotations.SerializedName

data class Data(@SerializedName("passwd")
                val passwd: String = "",
                @SerializedName("id")
                val id: String = "",
                @SerializedName("email")
                val email: String = "")
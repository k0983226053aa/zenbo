package com.cych.cychzenbo.model.JSONData.LoginDS

import com.google.gson.annotations.SerializedName

data class LoginDS(@SerializedName("msg")
                   val msg: String = "",
                   @SerializedName("code")
                   val code: String = "",
                   @SerializedName("data")
                   val data: Data)
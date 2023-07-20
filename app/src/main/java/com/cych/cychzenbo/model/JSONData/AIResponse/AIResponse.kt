package com.cych.cychzenbo.model.JSONData.AIResponse

import com.google.gson.annotations.SerializedName

data class AIResponse(@SerializedName("msg")
                      val msg: String = "",
                      @SerializedName("code")
                      val code: String = "",
                      @SerializedName("data")
                      val data: List<String>?)
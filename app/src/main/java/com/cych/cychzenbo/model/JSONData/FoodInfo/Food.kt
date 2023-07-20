package com.cych.cychzenbo.model.JSONData.FoodInfo

import com.google.gson.annotations.SerializedName

data class Food(@SerializedName("msg")
                val msg: String = "",
                @SerializedName("code")
                val code: String = "",
                @SerializedName("data")
                val data: List<DataItem>?)
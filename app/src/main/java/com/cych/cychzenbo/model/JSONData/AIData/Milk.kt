package com.cych.cychzenbo.model.JSONData.AIData

import com.google.gson.annotations.SerializedName

data class Milk(@SerializedName("amount")
                val amount: Int = 0,
                @SerializedName("feq")
                val feq: Int = 0,
                @SerializedName("amountShow")
                val amountShow: String = "",
                @SerializedName("name")
                val name: String = "",
                @SerializedName("cal")
                val cal: Int = 0)
package com.cych.cychzenbo.model.JSONData.AIData

import com.cych.cychzenbo.model.JSONData.FoodInfo.DataItem
import com.google.gson.annotations.SerializedName

data class Grain(@SerializedName("amount")
                 val amount: Int = 0,
                 @SerializedName("feq")
                 val feq: Int = 0,
                 @SerializedName("amountShow")
                 val amountShow: String = "",
                 @SerializedName("name")
                 var name: String = "",
                 @SerializedName("cal")
                 val cal: Int = 0)
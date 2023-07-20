package com.cych.cychzenbo.model.JSONData.FoodAmount

import com.google.gson.annotations.SerializedName

data class FoodAmountDataItem(@SerializedName("unit")
                              val unit: String = "",
                              @SerializedName("amount")
                              val amount: Int = 0,
                              @SerializedName("type")
                              val type: String = "")
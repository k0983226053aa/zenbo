package com.cych.cychzenbo.model.JSONData.FoodAmount

import com.google.gson.annotations.SerializedName

data class FoodAmount(@SerializedName("msg")
                      val msg: String = "",
                      @SerializedName("code")
                      val code: String = "",
                      @SerializedName("data")
                      val data: List<FoodAmountDataItem>?)
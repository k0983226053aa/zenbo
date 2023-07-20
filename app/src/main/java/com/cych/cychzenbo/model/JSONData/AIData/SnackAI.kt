package com.cych.cychzenbo.model.JSONData.AIData

import com.google.gson.annotations.SerializedName

data class SnackAI(@SerializedName("meat")
                   val meat: Int = 0,
                   @SerializedName("fat")
                   val fat: Int = 0,
                   @SerializedName("milk")
                   val milk: Int = 0,
                   @SerializedName("grain")
                   var grain: Int = 0,
                   @SerializedName("vegetable")
                   val vegetable: Int = 0,
                   @SerializedName("fruit")
                   val fruit: Int = 0,
                   @SerializedName("snack")
                   val snack: Int = 0)
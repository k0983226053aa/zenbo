package com.cych.cychzenbo.model.JSONData.BasicInfo

import com.google.gson.annotations.SerializedName

data class Breakfast(@SerializedName("meat")
                     val meat: Int = 0,
                     @SerializedName("fat")
                     val fat: Int = 0,
                     @SerializedName("milk")
                     val milk: Int = 0,
                     @SerializedName("grain")
                     val grain: Int = 0,
                     @SerializedName("vegetable")
                     val vegetable: Int = 0)
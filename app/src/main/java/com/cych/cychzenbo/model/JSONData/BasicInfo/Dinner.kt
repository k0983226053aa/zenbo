package com.cych.cychzenbo.model.JSONData.BasicInfo

import com.google.gson.annotations.SerializedName

data class Dinner(@SerializedName("meat")
                  val meat: Int = 0,
                  @SerializedName("fat")
                  val fat: Int = 0,
                  @SerializedName("grain")
                  val grain: Int = 0,
                  @SerializedName("vegetable")
                  val vegetable: Int = 0)
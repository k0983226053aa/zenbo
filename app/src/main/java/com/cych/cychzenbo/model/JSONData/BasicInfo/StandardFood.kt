package com.cych.cychzenbo.model.JSONData.BasicInfo

import com.google.gson.annotations.SerializedName

data class StandardFood(@SerializedName("numOfMilk")
                        val numOfMilk: Int = 0,
                        @SerializedName("numOfMeat")
                        val numOfMeat: String = "",
                        @SerializedName("numOfVegetable")
                        val numOfVegetable: Int = 0,
                        @SerializedName("numOfGrain")
                        val numOfGrain: String = "",
                        @SerializedName("numOfFruit")
                        val numOfFruit: Int = 0,
                        @SerializedName("numOfFat")
                        val numOfFat: String = "")
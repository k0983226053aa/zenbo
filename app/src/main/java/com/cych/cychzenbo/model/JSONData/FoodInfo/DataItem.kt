package com.cych.cychzenbo.model.JSONData.FoodInfo

import com.google.gson.annotations.SerializedName

data class DataItem(@SerializedName("foodType")
                    val foodType: String = "",
                    @SerializedName("weight")
                    val weight: Float = 0.0f,
                    @SerializedName("k")
                    val k: Float = 0.0f,
                    @SerializedName("fibert")
                    val fibert: Float = 0.0f,
                    @SerializedName("foodSubType")
                    val foodSubType: String = "",
                    @SerializedName("carbohydrate")
                    val carbohydrate: Float = 0.0f,
                    @SerializedName("p")
                    val p: Float = 0.0f,
                    @SerializedName("protein")
                    val protein: Float = 0.0f,
                    @SerializedName("name")
                    val name: String = "",
                    @SerializedName("calorie")
                    val calorie: Int = 0,
                    @SerializedName("fat")
                    val fat: Float = 0.0f,
                    @SerializedName("id")
                    val id: String = "",
                    @SerializedName("ca")
                    val ca: Float = 0.0f,
                    @SerializedName("fe")
                    val fe: Float = 0.0f)
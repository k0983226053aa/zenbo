package com.cych.cychzenbo.model.JSONData.BasicInfo

import com.google.gson.annotations.SerializedName

data class BasicInfo(@SerializedName("lunch")
                     val lunch: Lunch,
                     @SerializedName("snack")
                     val snack: Snack,
                     @SerializedName("calories")
                     val calories: Int = 0,
                     @SerializedName("breakfast")
                     val breakfast: Breakfast,
                     @SerializedName("standardFood")
                     val standardFood: StandardFood,
                     @SerializedName("dinner")
                     val dinner: Dinner,
                     @SerializedName("bmi")
                     val bmi: String = "")
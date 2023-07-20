package com.cych.cychzenbo.model.JSONData.AIData

import com.google.gson.annotations.SerializedName

data class StandardPlan(@SerializedName("lunch")
                        val lunch: Lunch,
                        @SerializedName("snack")
                        val snack: Snack,
                        @SerializedName("breakfast")
                        val breakfast: Breakfast,
                        @SerializedName("dinner")
                        val dinner: Dinner)
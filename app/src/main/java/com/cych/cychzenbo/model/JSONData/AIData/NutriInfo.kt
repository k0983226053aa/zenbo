package com.cych.cychzenbo.model.JSONData.AIData

import com.google.gson.annotations.SerializedName

data class NutriInfo(@SerializedName("lunch")
                     val lunch: Lunch,
                     @SerializedName("snack")
                     val snack: SnackAI,
                     @SerializedName("breakfast")
                     val breakfast: Breakfast,
                     @SerializedName("dinner")
                     val dinner: Dinner)
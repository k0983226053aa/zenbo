package com.cych.cychzenbo.model.JSONData.AIData

import com.google.gson.annotations.SerializedName

data class AIData(@SerializedName("problem")
                  val problem: Int = 0,
                  @SerializedName("disease")
                  val disease: Int = 0,
                  @SerializedName("gender")
                  val gender: Int = 0,
                  @SerializedName("nutriInfo")
                  val nutriInfo: NutriInfo,
                  @SerializedName("weight")
                  val weight: Int = 0,
                  @SerializedName("active")
                  val active: Int = 0,
                  @SerializedName("standardPlan")
                  val standardPlan: StandardPlan,
                  @SerializedName("age")
                  val age: Int = 0,
                  @SerializedName("height")
                  val height: Int = 0,
                  @SerializedName("cal")
                  val cal: Int = 0)
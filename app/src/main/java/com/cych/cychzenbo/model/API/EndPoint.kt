package com.cych.cychzenbo.model.API

import com.cych.cychzenbo.model.JSONData.AIResponse.AIResponse
import retrofit2.Call
import retrofit2.http.*
import com.cych.cychzenbo.model.JSONData.LoginDS.LoginDS
import com.cych.cychzenbo.model.JSONData.BasicInfo.BasicInfo
import com.cych.cychzenbo.model.JSONData.FoodAmount.FoodAmount
import com.cych.cychzenbo.model.JSONData.FoodInfo.Food
import okhttp3.RequestBody
import org.json.JSONObject

interface  EndPoint {
    @FormUrlEncoded
    @POST("/getUser")
    fun getUser(@Field("email") email:String) : Call<LoginDS>

    @FormUrlEncoded
    @POST("/GetNutritionInfo")
    fun getBasicInfo(@Field("age") age:Int, @Field("gender") gender:String, @Field("height") height:Int, @Field("weight") weight:Int, @Field("PA") PA:Float) : Call<BasicInfo>

    @FormUrlEncoded
    @POST("/getAllFoodInfo")
    fun getFoodInfo(@Field("foodType") foodType:String) : Call<Food>

    @FormUrlEncoded
    @POST("/getFoodSubTypeAmount")
    fun getFoodSubTypeAmount(@Field("foodSubType") foodSubType:String) : Call<FoodAmount>

//    @FormUrlEncoded
    @Headers( "Content-Type: application/json; charset=utf-8")
    @POST("/aiModule")
    fun aiModule(@Body body: RequestBody) : Call<AIResponse>
}
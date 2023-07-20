package com.cych.cychzenbo.model.GetDataModel

import android.util.Log
import com.cych.cychzenbo.allinterface.getDataInterface.getDataInterface
import com.cych.cychzenbo.model.JSONData.BasicInfo.BasicInfo
import com.cych.cychzenbo.model.API.RestAPI
import com.cych.cychzenbo.model.API.EndPoint
import com.cych.cychzenbo.model.JSONData.FoodInfo.DataItem
import com.cych.cychzenbo.model.JSONData.FoodInfo.Food

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetDataModel : getDataInterface.getDataModel{
    private var basicInfo: BasicInfo? = null
    private var foodInfo: List<DataItem>? = null

    override fun getBasicInfoResult() = basicInfo
    override fun getFoodInfoResult() = foodInfo
    private var apiClient:EndPoint? = null

    init{
        apiClient = RestAPI.client.create(EndPoint::class.java)
    }

    override fun getBasicInfo(
        age: Int,
        gender: String,
        height: Int,
        weight: Int,
        PA: Float,
        disease: Int,
        presenter: getDataInterface.getDataPresener
    ) {
        val call = apiClient?.getBasicInfo(age, gender, height, weight, PA)
        call?.enqueue(object : Callback<BasicInfo>{
            override fun onFailure(call: Call<BasicInfo>, t: Throwable) {
                Log.d("failure", t.toString())
            }

            override fun onResponse(call: Call<BasicInfo>, response: Response<BasicInfo>) {
                if(response.isSuccessful){
                    var results = response.body()
//                    Log.d("Success", results?.toString())
                    basicInfo = results
                    presenter.deliveryBasicInfo()
                }
            }
        })
    }

    override fun getFoodInfo(foodType: String, presenter: getDataInterface.getDataPresener) {
        val call = apiClient?.getFoodInfo(foodType)
        call?.enqueue(object : Callback<Food>{
            override fun onFailure(call: Call<Food>, t: Throwable) {
                Log.d("[getFoodInfo] failure", t.toString())
            }

            override fun onResponse(call: Call<Food>, response: Response<Food>) {
                if(response.isSuccessful){
                    var results = response.body()?.data
                    foodInfo = results

//                    Log.d("[getFoodInfo] Response", response.body().toString())

                    if (foodType == "乳品類"){
                        presenter.deliverMilkInfo()
                    }
                    else if(foodType == "全榖雜糧類"){
                        presenter.deliverGrainInfo()
                    }
                    else if(foodType == "豆魚蛋肉類"){
                        presenter.deliverMeatInfo()
                    }
                    else if(foodType == "蔬菜類"){
                        presenter.deliverVegetableInfo()
                    }
                    else if(foodType == "水果類"){
                        presenter.deliverFruitInfo()
                    }
                    else if(foodType == "油脂與堅果種子類"){
                        presenter.deliverFatInfo()
                    }
                    else if(foodType == "小吃"){
                        presenter.deliverSnackInfo()
                    }


                }
            }
        })
    }
}
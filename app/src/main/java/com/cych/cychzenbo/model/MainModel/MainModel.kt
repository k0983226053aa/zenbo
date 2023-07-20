package com.cych.cychzenbo.model.MainModel

import android.util.Log
import com.cych.cychzenbo.allinterface.GlobalVariable

import com.cych.cychzenbo.allinterface.MainInterface.MainInterface

import com.cych.cychzenbo.model.API.RestAPI
import com.cych.cychzenbo.model.API.EndPoint
import com.cych.cychzenbo.model.JSONData.LoginDS.LoginDS
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainModel : MainInterface.mainModel {
    private var status = ""
    override fun getLoginStatus() = status

    private var apiClient:EndPoint? = null

    init {
        apiClient = RestAPI.client.create(EndPoint::class.java)
    }

    override fun getUser(email: String, passwd: String, presenter: MainInterface.mainPresenter) {
        val call = apiClient?.getUser(email)

        call?.enqueue(object : Callback<LoginDS>{
            override fun onFailure(call: Call<LoginDS>, t: Throwable) {
                Log.d("failure", t.toString())
            }

            override fun onResponse(call: Call<LoginDS>, response: Response<LoginDS>) {
                if (response?.isSuccessful!!){
                    var results = response?.body()
//                    Log.d("Success", results?.data.toString())

                    if(results?.code == "400"){
                        status = "查無此帳號"
                    }
                    else if(passwd == results?.data?.passwd){
                        status = "true"
                    }
                    else{
                        status = "密碼錯誤"
                    }
                    presenter.deliveryStatus()
                }
            }
        })
    }
}
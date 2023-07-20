package com.cych.cychzenbo.model.BreakfastModel

import android.util.Log
import android.widget.Toast
import com.cych.cychzenbo.allinterface.BreakfastInterface.BreakfastInterface
import com.cych.cychzenbo.model.API.EndPoint
import com.cych.cychzenbo.model.API.RestAPI
import com.cych.cychzenbo.model.JSONData.AIResponse.AIResponse
import com.cych.cychzenbo.presenter.BreakfastPresenter.BreakfastPresenter
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BreakfastModel:BreakfastInterface.BIModel {
    private var apiClient: EndPoint? = null
    private var tmp_plan : List<String>? = null

    override fun getPlanResult() = tmp_plan

    init{
        apiClient = RestAPI.client.create(EndPoint::class.java)
    }

    override fun getPlan(body: JSONObject, presenter: BreakfastPresenter) {
        Log.d("Breakfast", "In Model")
        var bodyRequest: RequestBody = RequestBody.create("application/json".toMediaTypeOrNull(), body.toString())
        val call = apiClient?.aiModule(bodyRequest)
        call?.enqueue(object : Callback<AIResponse>{
            override fun onFailure(call: Call<AIResponse>, t: Throwable) {
                Log.d("[getPlan] failure", t.toString())
            }

            override fun onResponse(call: Call<AIResponse>, response: Response<AIResponse>) {
                if(response.isSuccessful){
                    var results = response.body()?.data
                    tmp_plan = results
                    presenter?.deliverPlan()
                    Log.d("[getPlan]", results.toString())
                }
            }
        })
    }
}
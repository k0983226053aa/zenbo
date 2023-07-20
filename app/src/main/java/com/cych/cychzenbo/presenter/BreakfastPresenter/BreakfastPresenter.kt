package com.cych.cychzenbo.presenter.BreakfastPresenter

import android.util.Log
import com.cych.cychzenbo.allinterface.BreakfastInterface.BreakfastInterface
import com.cych.cychzenbo.allinterface.getDataInterface.getDataInterface
import com.cych.cychzenbo.model.BreakfastModel.BreakfastModel
import com.cych.cychzenbo.model.GetDataModel.GetDataModel
import com.google.gson.JsonObject
import org.json.JSONObject


class BreakfastPresenter(view: BreakfastInterface.BIView) : BreakfastInterface.BIPresenter {
    private val view: BreakfastInterface.BIView = view
    private val model: BreakfastInterface.BIModel = BreakfastModel()

    override fun getPlan(body: JSONObject) {
        Log.d("Breakfast", "In Presenter")
        Log.d("body", body.toString())
        model?.getPlan(body, this)
    }

    override fun deliverPlan() {
        view.receivePlan()
    }

    override fun showPlan() = model?.getPlanResult()
}
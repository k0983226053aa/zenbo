package com.cych.cychzenbo.allinterface.BreakfastInterface

import com.cych.cychzenbo.presenter.BreakfastPresenter.BreakfastPresenter
import com.google.gson.JsonObject
import org.json.JSONObject

interface BreakfastInterface {
    interface BIModel{
        fun getPlan(body: JSONObject, presenter:BreakfastPresenter)
        fun getPlanResult():List<String>?
    }

    interface BIView{
        fun receivePlan()
    }

    interface BIPresenter{
        fun deliverPlan()
        fun showPlan():List<String>?
        fun getPlan(body:JSONObject)
    }
}
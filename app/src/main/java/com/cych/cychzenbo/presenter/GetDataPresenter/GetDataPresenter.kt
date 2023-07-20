package com.cych.cychzenbo.presenter.GetDataPresenter

import android.util.Log
import com.cych.cychzenbo.allinterface.getDataInterface.getDataInterface
import com.cych.cychzenbo.model.GetDataModel.GetDataModel
import com.cych.cychzenbo.model.JSONData.BasicInfo.BasicInfo
import com.cych.cychzenbo.model.JSONData.FoodInfo.DataItem

class GetDataPresenter(view:getDataInterface.getDataView) : getDataInterface.getDataPresener{
    private val view:getDataInterface.getDataView = view
    private val model: getDataInterface.getDataModel = GetDataModel()

    override fun getBasicInfo(
        age: Int,
        gender: String,
        height: Int,
        weight: Int,
        PA: Float,
        disease: Int
    ) {
        model?.getBasicInfo(age, gender, height, weight, PA, disease, this)
    }

    override fun deliveryBasicInfo() {
        view.receiveData()
    }
    override fun showBasicInfo() = model?.getBasicInfoResult()

    override fun deliverMilkInfo() {
        view.receiveMilkInfo()
    }
    override fun showMilkInfo() = model?.getFoodInfoResult()

    override fun deliverGrainInfo() {
        view.receiveGrainInfo()
    }
    override fun showGrainInfo() = model?.getFoodInfoResult()

    override fun deliverMeatInfo() {
        view.receiveMeatInfo()
    }
    override fun showMeatInfo() = model?.getFoodInfoResult()

    override fun deliverVegetableInfo() {
        view.receiveVegetableInfo()
    }
    override fun showVegetableInfo() = model?.getFoodInfoResult()

    override fun deliverFatInfo() {
        view.receiveFatInfo()
    }
    override fun showFatInfo() = model?.getFoodInfoResult()

    override fun deliverFruitInfo() {
        view.receiveFruitInfo()
    }
    override fun showFruitInfo() = model?.getFoodInfoResult()

    override fun deliverSnackInfo() {
        view.receiveSnackInfo()
    }
    override fun showSnackInfo() = model?.getFoodInfoResult()



    override fun getFoodInfo(foodType: String) {
        model?.getFoodInfo(foodType, this)
    }
}
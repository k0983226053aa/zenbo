package com.cych.cychzenbo.allinterface.getDataInterface

import com.cych.cychzenbo.model.JSONData.BasicInfo.BasicInfo
import com.cych.cychzenbo.model.JSONData.FoodInfo.DataItem

interface getDataInterface{
    interface getDataModel{
        fun getBasicInfo(age:Int, gender:String, height:Int, weight: Int, PA:Float, disease: Int, presenter:getDataPresener)
        fun getBasicInfoResult():BasicInfo?
        fun getFoodInfoResult(): List<DataItem>?
        fun getFoodInfo(foodType:String, presenter: getDataPresener)
    }

    interface getDataView{
        fun receiveData()
        fun receiveMilkInfo()
        fun receiveGrainInfo()
        fun receiveMeatInfo()
        fun receiveVegetableInfo()
        fun receiveFatInfo()
        fun receiveFruitInfo()
        fun receiveSnackInfo()
    }

    interface getDataPresener{
        fun showBasicInfo():BasicInfo?
        fun getBasicInfo(age:Int, gender:String, height:Int, weight: Int, PA:Float, disease: Int)
        fun deliveryBasicInfo()

        fun showMilkInfo(): List<DataItem>?
        fun deliverMilkInfo()

        fun showGrainInfo(): List<DataItem>?
        fun deliverGrainInfo()

        fun showMeatInfo(): List<DataItem>?
        fun deliverMeatInfo()

        fun showFatInfo(): List<DataItem>?
        fun deliverFatInfo()

        fun showVegetableInfo(): List<DataItem>?
        fun deliverVegetableInfo()

        fun showFruitInfo(): List<DataItem>?
        fun deliverFruitInfo()

        fun showSnackInfo(): List<DataItem>?
        fun deliverSnackInfo()

        fun getFoodInfo(foodType:String)
    }
}
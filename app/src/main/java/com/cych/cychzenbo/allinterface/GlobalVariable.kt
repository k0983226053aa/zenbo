package com.cych.cychzenbo.allinterface

import android.app.Application
import com.cych.cychzenbo.model.JSONData.BasicInfo.BasicInfo
import com.cych.cychzenbo.model.JSONData.FoodInfo.DataItem
import org.json.JSONObject

class GlobalVariable : Application() {
    private var age:Int = 0
    private var gender: String = "male"
    private var height: Int = 0
    private var weight: Int = 0
    private var PA: Float = 1.05F
    private var disease: Int = 0
    private var basicInfo: BasicInfo? = null

    private var milkInfo: List<DataItem>? = null
    private var grainInfo: List<DataItem>? = null
    private var meatInfo: List<DataItem>? = null
    private var vegetableInfo: List<DataItem>? = null
    private var fatInfo: List<DataItem>? = null
    private var fruitInfo: List<DataItem>? = null
    private var snackInfo: List<DataItem>? = null

    private var grainList: List<String>? = null
    private var meatList: List<String>? = null
    private var vegetableList: List<String>? = null
    private var milkList: List<String>? = null
    private var fatList: List<String>? = null
    private var fruitList: List<String>? = null
    private var snackList: List<String>? = null

    private var planJSON : JSONObject? = null


    fun setAge(age: Int){
        this.age = age
    }

    fun setGender(gender:String){
        this.gender = gender
    }

    fun setHeight(height: Int){
        this.height = height
    }

    fun setWeight(weight: Int){
        this.weight = weight
    }

    fun setPA(PA: Float){
        this.PA = PA
    }

    fun setDisease(set:String, disease: Int) {
        if(set == "set"){
            this.disease = this.disease or disease
        }
        else if(set == "clean"){
            this.disease = this.disease and disease.inv()
        }
    }

    fun setBasicInfo(basicInfo: BasicInfo?){
        this.basicInfo = basicInfo
    }

    fun setMilkInfo(milkInfo: List<DataItem>?){
        this.milkInfo = milkInfo
    }

    fun setGrainInfo(grainInfo: List<DataItem>?){
        this.grainInfo = grainInfo
    }

    fun setMeatInfo(meatInfo: List<DataItem>?){
        this.meatInfo = meatInfo
    }

    fun setVegetableInfo(vegetableInfo: List<DataItem>?){
        this.vegetableInfo = vegetableInfo
    }

    fun setFatInfo(fatInfo: List<DataItem>?){
        this.fatInfo = fatInfo
    }

    fun setFruitInfo(fruitInfo: List<DataItem>?){
        this.fruitInfo = fruitInfo
    }

    fun setSnackInfo(snackInfo: List<DataItem>?){
        this.snackInfo = snackInfo
    }

    fun setGrainList(grainList: MutableList<String>){
        this.grainList = grainList
    }

    fun setMeatList(meatList: MutableList<String>){
        this.meatList = meatList
    }

    fun setVegetableList(vegetableList: MutableList<String>){
        this.vegetableList = vegetableList
    }

    fun setMilkList(milkList: MutableList<String>){
        this.milkList = milkList
    }

    fun setFatList(fatList: MutableList<String>){
        this.fatList = fatList
    }

    fun setFruitList(fruitList: MutableList<String>){
        this.fruitList = fruitList
    }

    fun setSnackList(snackList: MutableList<String>){
        this.snackList = snackList
    }

    fun setPlanJSON(body:JSONObject){
        this.planJSON = body
    }

    fun getAge(): Int {
        return age
    }

    fun getGender(): String {
        return gender
    }

    fun getHeight() : Int{
        return height
    }

    fun getWeight() : Int{
        return weight
    }

    fun getPA() : Float{
        return PA
    }

    fun getDisease() : Int{
        return disease
    }

    fun getBasicInfo() : BasicInfo?{
        return basicInfo
    }

    fun getMilkInfo(): List<DataItem>?{
        return milkInfo
    }

    fun getMeatInfo(): List<DataItem>?{
        return meatInfo
    }

    fun getGrainInfo(): List<DataItem>?{
        return grainInfo
    }

    fun getVegetableInfo(): List<DataItem>?{
        return vegetableInfo
    }

    fun getFatInfo(): List<DataItem>?{
        return fatInfo
    }

    fun getFruitInfo(): List<DataItem>?{
        return fruitInfo
    }

    fun getSnackInfo(): List<DataItem>?{
        return snackInfo
    }

    fun getGrainList(): List<String>? {
        return grainList
    }

    fun getMeatList(): List<String>? {
        return meatList
    }

    fun getVegetableList(): List<String>? {
        return vegetableList
    }

    fun getMilkList(): List<String>? {
        return milkList
    }

    fun getFatList(): List<String>? {
        return fatList
    }

    fun getFruitList(): List<String>? {
        return fruitList
    }

    fun getSnackList(): List<String>? {
        return snackList
    }

    fun getPlanJSON() : JSONObject? {
        return planJSON
    }
}
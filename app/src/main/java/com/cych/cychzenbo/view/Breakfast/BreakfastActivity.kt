package com.cych.cychzenbo.view.Breakfast

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.cych.cychzenbo.R
import com.cych.cychzenbo.allinterface.BreakfastInterface.BreakfastInterface
import com.cych.cychzenbo.allinterface.GlobalVariable
import com.cych.cychzenbo.model.JSONData.AIData.*
import com.cych.cychzenbo.model.JSONData.FoodInfo.DataItem
import com.cych.cychzenbo.presenter.BreakfastPresenter.BreakfastPresenter
import com.cych.cychzenbo.presenter.GetDataPresenter.GetDataPresenter
import com.cych.cychzenbo.view.ShowResultActivity.ShowResultActivity
import com.google.gson.Gson
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_breakfast.*
import org.json.JSONObject

class BreakfastActivity : AppCompatActivity(), BreakfastInterface.BIView {
    private var global: GlobalVariable? = null
    private var aiData: JSONObject? = null
    private var standardPlan : JSONObject? = null
    private var nutriInfo : JSONObject? = null
    private var nutriInfoBreakfast : JSONObject? = null
    private var nutriInfoLunch : JSONObject? = null
    private var nutriInfoDinner : JSONObject? = null
    private var nutriInfoSnack : JSONObject? = null

    private var presenter : BreakfastPresenter? = null

    override fun receivePlan() {
        var plan:List<String>? = presenter?.showPlan()
        val plan_json : JSONObject? = JSONObject(plan?.get(0))
        global?.setPlanJSON(plan_json!!)
        startActivity(Intent(this, ShowResultActivity::class.java))
//        Log.d("B - Activity", plan_json.toString())
//        Toast.makeText(this, plan.toString(), Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_breakfast)

        var gv: GlobalVariable = applicationContext as GlobalVariable
        global = gv
        initialize()
        var grainList: MutableList<String>
        var meatList: MutableList<String>
        var vegetableList: MutableList<String>
        var milkList: MutableList<String>
        var fruitList: MutableList<String>
        var fatList: MutableList<String>
        var snackList: MutableList<String>

        grainList = getGrainName(gv.getGrainInfo())
        meatList = getMeatName(gv.getMeatInfo())
        vegetableList = getVegetableName(gv.getVegetableInfo())
        milkList = getMilkName(gv.getMilkInfo())
        fruitList = getFruitName(gv.getFruitInfo())
        fatList = getFatName(gv.getFatInfo())
        snackList = getSnackName(gv.getSnackInfo())

        aiData = JSONObject("{}")
        standardPlan = JSONObject("{}")
        nutriInfo = JSONObject("{}")
        nutriInfoBreakfast = JSONObject("{}")
        nutriInfoLunch = JSONObject("{}")
        nutriInfoDinner = JSONObject("{}")
        nutriInfoSnack = JSONObject("{}")

        standardPlan?.put("breakfast", gv.getBasicInfo()?.breakfast)
        standardPlan?.put("lunch", gv.getBasicInfo()?.lunch)
        standardPlan?.put("dinner", gv.getBasicInfo()?.dinner)
        standardPlan?.put("snack", gv.getBasicInfo()?.snack)

        aiData?.put("age", gv.getAge())

        var gender:Int = 0

        if (gv.getGender() == "male"){
            gender = 1
        }
        else if (gv.getGender() == "female"){
            gender = 2
        }
        aiData?.put("gender", gender)
        aiData?.put("height", gv.getHeight())
        aiData?.put("weight", gv.getWeight())
        aiData?.put("cal", gv.getBasicInfo()?.calories)
        var pa:Int
        if (gv.getPA() == 1.05f){
            pa = 1
        }else if(gv.getPA() == 1.15f){
            pa = 2
        }
        else if(gv.getPA() == 1.25f){
            pa = 3
        }
        else if(gv.getPA() == 1.35f){
            pa = 4
        }
        else{
            pa = 0
        }
        aiData?.put("active", pa)
        aiData?.put("problem", 0)
        aiData?.put("disease", gv.getDisease())
        var tmp_Plan = Gson().toJson(standardPlan)
        var tmp_Plan_Obj =JSONObject(tmp_Plan).getJSONObject("nameValuePairs")
        aiData?.put("standardPlan", tmp_Plan_Obj)


        val grainAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, grainList)
        val meatAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, meatList)
        val vegetableAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, vegetableList)
        val milkAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, milkList)
        val fatAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, fatList)
        val fruitAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, fruitList)
        val snackAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, snackList)

        breakfastGrainspinner.setAdapter(grainAdapter)
        breakfastMeatspinner.setAdapter(meatAdapter)
        breakfastVegetablespinner.setAdapter(vegetableAdapter)
        breakfastMilkspinner.setAdapter(milkAdapter)
        breakfastFatspinner.setAdapter(fatAdapter)
        breakfastFruitspinner.setAdapter(fruitAdapter)
        breakfastSnackspinner.setAdapter(snackAdapter)

        lunchGrainspinner.setAdapter(grainAdapter)
        lunchMeatspinner.setAdapter(meatAdapter)
        lunchVegetablespinner.setAdapter(vegetableAdapter)
        lunchMilkspinner.setAdapter(milkAdapter)
        lunchFatspinner.setAdapter(fatAdapter)
        lunchFruitspinner.setAdapter(fruitAdapter)
        lunchSnackspinner.setAdapter(snackAdapter)

        dinnerGrainspinner.setAdapter(grainAdapter)
        dinnerMeatspinner.setAdapter(meatAdapter)
        dinnerVegetablespinner.setAdapter(vegetableAdapter)
        dinnerMilkspinner.setAdapter(milkAdapter)
        dinnerFatspinner.setAdapter(fatAdapter)
        dinnerFruitspinner.setAdapter(fruitAdapter)
        dinnerSnackspinner.setAdapter(snackAdapter)

        snackGrainspinner.setAdapter(grainAdapter)
        snackMeatspinner.setAdapter(meatAdapter)
        snackVegetablespinner.setAdapter(vegetableAdapter)
        snackMilkspinner.setAdapter(milkAdapter)
        snackFatspinner.setAdapter(fatAdapter)
        snackFruitspinner.setAdapter(fruitAdapter)
        snackSnackspinner.setAdapter(snackAdapter)

        breakfastGrainspinner.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val tmp_result = gv.getGrainInfo()
                var grain = Grain(0, 0, "", tmp_result?.get(position)?.name.toString(), 0)
                var tmp_output : JSONObject? = null
                tmp_output  = JSONObject("{}")
                tmp_output.put("name", grain.name)
                tmp_output.put("amountShow", "")
                tmp_output.put("amount", 0)
                tmp_output.put("cal", 0)
                tmp_output.put("feq", 0)
                nutriInfoBreakfast!!.put("grain", tmp_output)
                nutriInfo!!.put("breakfast", nutriInfoBreakfast)
                aiData!!.put("nutriInfo", nutriInfo)
                Log.d("Breakfast[Grain]", aiData.toString())
            }
        }

        lunchGrainspinner.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val tmp_result = gv.getGrainInfo()
                var grain = Grain(0, 0, "", tmp_result?.get(position)?.name.toString(), 0)
                var tmp_output : JSONObject? = null
                tmp_output  = JSONObject("{}")
                tmp_output!!.put("name", grain.name)
                tmp_output!!.put("amountShow", "")
                tmp_output!!.put("amount", 0)
                tmp_output!!.put("cal", 0)
                tmp_output!!.put("feq", 0)
                nutriInfoLunch!!.put("grain", tmp_output)
                nutriInfo!!.put("lunch", nutriInfoLunch)
                aiData!!.put("nutriInfo", nutriInfo)
                Log.d("Lunch[Grain]", aiData.toString())
            }
        }

        dinnerGrainspinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val tmp_result = gv.getGrainInfo()
                var grain = Grain(0, 0, "", tmp_result?.get(position)?.name.toString(), 0)
                var tmp_output : JSONObject? = null
                tmp_output  = JSONObject("{}")
                tmp_output!!.put("name", grain.name)
                tmp_output!!.put("amountShow", "")
                tmp_output!!.put("amount", 0)
                tmp_output!!.put("cal", 0)
                tmp_output!!.put("feq", 0)
                nutriInfoDinner!!.put("grain", tmp_output)
                nutriInfo!!.put("dinner", nutriInfoDinner)
                aiData!!.put("nutriInfo", nutriInfo)
                Log.d("Dinner[Grain]", aiData.toString())
            }
        }

        snackGrainspinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val tmp_result = gv.getGrainInfo()
                var grain = Grain(0, 0, "", tmp_result?.get(position)?.name.toString(), 0)
                var tmp_output : JSONObject? = null
                tmp_output  = JSONObject("{}")
                tmp_output!!.put("name", grain.name)
                tmp_output!!.put("amountShow", "")
                tmp_output!!.put("amount", 0)
                tmp_output!!.put("cal", 0)
                tmp_output!!.put("feq", 0)
                nutriInfoSnack!!.put("grain", tmp_output)
                nutriInfo!!.put("snack", nutriInfoSnack)
                aiData!!.put("nutriInfo", nutriInfo)
                Log.d("Snack[Grain]", aiData.toString())
            }
        }

        breakfastMeatspinner.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val tmp_result = gv.getMeatInfo()
                var meat = Meat(0, 0, "", tmp_result?.get(position)?.name.toString(), 0)
                var tmp_output : JSONObject? = null
                tmp_output  = JSONObject("{}")
                tmp_output!!.put("name", meat.name)
                tmp_output!!.put("amountShow", "")
                tmp_output!!.put("amount", 0)
                tmp_output!!.put("cal", 0)
                tmp_output!!.put("feq", 0)
                nutriInfoBreakfast!!.put("meat", tmp_output)
                nutriInfo!!.put("breakfast", nutriInfoBreakfast)
                aiData!!.put("nutriInfo", nutriInfo)
                Log.d("Breakfast[Meat]", aiData.toString())
            }
        }

        lunchMeatspinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val tmp_result = gv.getMeatInfo()
                var meat = Meat(0, 0, "", tmp_result?.get(position)?.name.toString(), 0)
                var tmp_output : JSONObject? = null
                tmp_output  = JSONObject("{}")
                tmp_output!!.put("name", meat.name)
                tmp_output!!.put("amountShow", "")
                tmp_output!!.put("amount", 0)
                tmp_output!!.put("cal", 0)
                tmp_output!!.put("feq", 0)
                nutriInfoLunch!!.put("meat", tmp_output)
                nutriInfo!!.put("lunch", nutriInfoLunch)
                aiData!!.put("nutriInfo", nutriInfo)
                Log.d("Lunch[Meat]", aiData.toString())
            }
        }

        dinnerMeatspinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val tmp_result = gv.getMeatInfo()
                var meat = Meat(0, 0, "", tmp_result?.get(position)?.name.toString(), 0)
                var tmp_output : JSONObject? = null
                tmp_output  = JSONObject("{}")
                tmp_output!!.put("name", meat.name)
                tmp_output!!.put("amountShow", "")
                tmp_output!!.put("amount", 0)
                tmp_output!!.put("cal", 0)
                tmp_output!!.put("feq", 0)
                nutriInfoDinner!!.put("meat", tmp_output)
                nutriInfo!!.put("dinner", nutriInfoDinner)
                aiData!!.put("nutriInfo", nutriInfo)
                Log.d("Dinner[Meat]", aiData.toString())
            }
        }

        snackMeatspinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val tmp_result = gv.getMeatInfo()
                var meat = Meat(0, 0, "", tmp_result?.get(position)?.name.toString(), 0)
                var tmp_output : JSONObject? = null
                tmp_output  = JSONObject("{}")
                tmp_output!!.put("name", meat.name)
                tmp_output!!.put("amountShow", "")
                tmp_output!!.put("amount", 0)
                tmp_output!!.put("cal", 0)
                tmp_output!!.put("feq", 0)
                nutriInfoSnack!!.put("meat", tmp_output)
                nutriInfo!!.put("snack", nutriInfoSnack)
                aiData!!.put("nutriInfo", nutriInfo)
                Log.d("Snack[Meat]", aiData.toString())
            }
        }

        breakfastVegetablespinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val tmp_result = gv.getVegetableInfo()
                var vegetable = Vegetable(0, 0, "", tmp_result?.get(position)?.name.toString(), 0)
                var tmp_output : JSONObject? = null
                tmp_output  = JSONObject("{}")
                tmp_output!!.put("name", vegetable.name)
                tmp_output!!.put("amountShow", "")
                tmp_output!!.put("amount", 0)
                tmp_output!!.put("cal", 0)
                tmp_output!!.put("feq", 0)
                nutriInfoBreakfast!!.put("vegetable", tmp_output)
                nutriInfo!!.put("breakfast", nutriInfoBreakfast)
                aiData!!.put("nutriInfo", nutriInfo)
            }
        }

        lunchVegetablespinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val tmp_result = gv.getVegetableInfo()
                var vegetable = Vegetable(0, 0, "", tmp_result?.get(position)?.name.toString(), 0)
                var tmp_output : JSONObject? = null
                tmp_output  = JSONObject("{}")
                tmp_output!!.put("name", vegetable.name)
                tmp_output!!.put("amountShow", "")
                tmp_output!!.put("amount", 0)
                tmp_output!!.put("cal", 0)
                tmp_output!!.put("feq", 0)
                nutriInfoLunch!!.put("vegetable", tmp_output)
                nutriInfo!!.put("lunch", nutriInfoLunch)
                aiData!!.put("nutriInfo", nutriInfo)
            }
        }

        dinnerVegetablespinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val tmp_result = gv.getVegetableInfo()
                var vegetable = Vegetable(0, 0, "", tmp_result?.get(position)?.name.toString(), 0)
                var tmp_output : JSONObject? = null
                tmp_output  = JSONObject("{}")
                tmp_output!!.put("name", vegetable.name)
                tmp_output!!.put("amountShow", "")
                tmp_output!!.put("amount", 0)
                tmp_output!!.put("cal", 0)
                tmp_output!!.put("feq", 0)
                nutriInfoDinner!!.put("vegetable", tmp_output)
                nutriInfo!!.put("dinner", nutriInfoDinner)
                aiData!!.put("nutriInfo", nutriInfo)
            }
        }

        snackVegetablespinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val tmp_result = gv.getVegetableInfo()
                var vegetable = Vegetable(0, 0, "", tmp_result?.get(position)?.name.toString(), 0)
                var tmp_output : JSONObject? = null
                tmp_output  = JSONObject("{}")
                tmp_output!!.put("name", vegetable.name)
                tmp_output!!.put("amountShow", "")
                tmp_output!!.put("amount", 0)
                tmp_output!!.put("cal", 0)
                tmp_output!!.put("feq", 0)
                nutriInfoSnack!!.put("vegetable", tmp_output)
                nutriInfo!!.put("snack", nutriInfoSnack)
                aiData!!.put("nutriInfo", nutriInfo)
            }
        }

        breakfastMilkspinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val tmp_result = gv.getMilkInfo()
                var milk = Milk(0, 0, "", tmp_result?.get(position)?.name.toString(), 0)
                var tmp_output : JSONObject? = null
                tmp_output  = JSONObject("{}")
                tmp_output!!.put("name", milk.name)
                tmp_output!!.put("amountShow", "")
                tmp_output!!.put("amount", 0)
                tmp_output!!.put("cal", 0)
                tmp_output!!.put("feq", 0)
                nutriInfoBreakfast!!.put("milk", tmp_output)
                nutriInfo!!.put("breakfast", nutriInfoBreakfast)
                aiData!!.put("nutriInfo", nutriInfo)
            }
        }

        lunchMilkspinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val tmp_result = gv.getMilkInfo()
                var milk = Milk(0, 0, "", tmp_result?.get(position)?.name.toString(), 0)
                var tmp_output : JSONObject? = null
                tmp_output  = JSONObject("{}")
                tmp_output!!.put("name", milk.name)
                tmp_output!!.put("amountShow", "")
                tmp_output!!.put("amount", 0)
                tmp_output!!.put("cal", 0)
                tmp_output!!.put("feq", 0)
                nutriInfoLunch!!.put("milk", tmp_output)
                nutriInfo!!.put("lunch", nutriInfoLunch)
                aiData!!.put("nutriInfo", nutriInfo)
            }
        }

        dinnerMilkspinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val tmp_result = gv.getMilkInfo()
                var milk = Milk(0, 0, "", tmp_result?.get(position)?.name.toString(), 0)
                var tmp_output : JSONObject? = null
                tmp_output  = JSONObject("{}")
                tmp_output!!.put("name", milk.name)
                tmp_output!!.put("amountShow", "")
                tmp_output!!.put("amount", 0)
                tmp_output!!.put("cal", 0)
                tmp_output!!.put("feq", 0)
                nutriInfoDinner!!.put("milk", tmp_output)
                nutriInfo!!.put("dinner", nutriInfoDinner)
                aiData!!.put("nutriInfo", nutriInfo)
            }
        }

        snackMilkspinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val tmp_result = gv.getMilkInfo()
                var milk = Milk(0, 0, "", tmp_result?.get(position)?.name.toString(), 0)
                var tmp_output : JSONObject? = null
                tmp_output  = JSONObject("{}")
                tmp_output!!.put("name", milk.name)
                tmp_output!!.put("amountShow", "")
                tmp_output!!.put("amount", 0)
                tmp_output!!.put("cal", 0)
                tmp_output!!.put("feq", 0)
                nutriInfoSnack!!.put("milk", tmp_output)
                nutriInfo!!.put("snack", nutriInfoSnack)
                aiData!!.put("nutriInfo", nutriInfo)
            }
        }

        breakfastFatspinner.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val tmp_result = gv.getFatInfo()
                var fat = Fat(0, 0, "", tmp_result?.get(position)?.name.toString(), 0)
                var tmp_output : JSONObject? = null
                tmp_output  = JSONObject("{}")
                tmp_output!!.put("name", fat.name)
                tmp_output!!.put("amountShow", "")
                tmp_output!!.put("amount", 0)
                tmp_output!!.put("cal", 0)
                tmp_output!!.put("feq", 0)
                nutriInfoBreakfast!!.put("fat", tmp_output)
                nutriInfo!!.put("breakfast", nutriInfoBreakfast)
                aiData!!.put("nutriInfo", nutriInfo)
            }
        }

        lunchFatspinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val tmp_result = gv.getFatInfo()
                var fat = Fat(0, 0, "", tmp_result?.get(position)?.name.toString(), 0)
                var tmp_output : JSONObject? = null
                tmp_output  = JSONObject("{}")
                tmp_output!!.put("name", fat.name)
                tmp_output!!.put("amountShow", "")
                tmp_output!!.put("amount", 0)
                tmp_output!!.put("cal", 0)
                tmp_output!!.put("feq", 0)
                nutriInfoLunch!!.put("fat", tmp_output)
                nutriInfo!!.put("lunch", nutriInfoLunch)
                aiData!!.put("nutriInfo", nutriInfo)
            }
        }

        dinnerFatspinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val tmp_result = gv.getFatInfo()
                var fat = Fat(0, 0, "", tmp_result?.get(position)?.name.toString(), 0)
                var tmp_output : JSONObject? = null
                tmp_output  = JSONObject("{}")
                tmp_output!!.put("name", fat.name)
                tmp_output!!.put("amountShow", "")
                tmp_output!!.put("amount", 0)
                tmp_output!!.put("cal", 0)
                tmp_output!!.put("feq", 0)
                nutriInfoDinner!!.put("fat", tmp_output)
                nutriInfo!!.put("dinner", nutriInfoDinner)
                aiData!!.put("nutriInfo", nutriInfo)
            }
        }

        snackFatspinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val tmp_result = gv.getFatInfo()
                var fat = Fat(0, 0, "", tmp_result?.get(position)?.name.toString(), 0)
                var tmp_output : JSONObject? = null
                tmp_output  = JSONObject("{}")
                tmp_output!!.put("name", fat.name)
                tmp_output!!.put("amountShow", "")
                tmp_output!!.put("amount", 0)
                tmp_output!!.put("cal", 0)
                tmp_output!!.put("feq", 0)
                nutriInfoSnack!!.put("fat", tmp_output)
                nutriInfo!!.put("snack", nutriInfoSnack)
                aiData!!.put("nutriInfo", nutriInfo)
            }
        }

        breakfastFruitspinner.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val tmp_result = gv.getFruitInfo()
                var fruit = Fruit(0, 0, "", tmp_result?.get(position)?.name.toString(), 0)
                var tmp_output : JSONObject? = null
                tmp_output  = JSONObject("{}")
                tmp_output!!.put("name", fruit.name)
                tmp_output!!.put("amountShow", "")
                tmp_output!!.put("amount", 0)
                tmp_output!!.put("cal", 0)
                tmp_output!!.put("feq", 0)
                nutriInfoBreakfast!!.put("fruit", tmp_output)
                nutriInfo!!.put("breakfast", nutriInfoBreakfast)
                aiData!!.put("nutriInfo", nutriInfo)
            }
        }

        lunchFruitspinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val tmp_result = gv.getFruitInfo()
                var fruit = Fruit(0, 0, "", tmp_result?.get(position)?.name.toString(), 0)
                var tmp_output : JSONObject? = null
                tmp_output  = JSONObject("{}")
                tmp_output!!.put("name", fruit.name)
                tmp_output!!.put("amountShow", "")
                tmp_output!!.put("amount", 0)
                tmp_output!!.put("cal", 0)
                tmp_output!!.put("feq", 0)
                nutriInfoLunch!!.put("fruit", tmp_output)
                nutriInfo!!.put("lunch", nutriInfoLunch)
                aiData!!.put("nutriInfo", nutriInfo)
            }
        }

        dinnerFruitspinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val tmp_result = gv.getFruitInfo()
                var fruit = Fruit(0, 0, "", tmp_result?.get(position)?.name.toString(), 0)
                var tmp_output : JSONObject? = null
                tmp_output  = JSONObject("{}")
                tmp_output!!.put("name", fruit.name)
                tmp_output!!.put("amountShow", "")
                tmp_output!!.put("amount", 0)
                tmp_output!!.put("cal", 0)
                tmp_output!!.put("feq", 0)
                nutriInfoDinner!!.put("fruit", tmp_output)
                nutriInfo!!.put("dinner", nutriInfoDinner)
                aiData!!.put("nutriInfo", nutriInfo)
            }
        }

        snackFruitspinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val tmp_result = gv.getFruitInfo()
                var fruit = Fruit(0, 0, "", tmp_result?.get(position)?.name.toString(), 0)
                var tmp_output : JSONObject? = null
                tmp_output  = JSONObject("{}")
                tmp_output!!.put("name", fruit.name)
                tmp_output!!.put("amountShow", "")
                tmp_output!!.put("amount", 0)
                tmp_output!!.put("cal", 0)
                tmp_output!!.put("feq", 0)
                nutriInfoSnack!!.put("fruit", tmp_output)
                nutriInfo!!.put("snack", nutriInfoSnack)
                aiData!!.put("nutriInfo", nutriInfo)
            }
        }

        breakfastSnackspinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val tmp_result = gv.getSnackInfo()
                var snack = Snack(0, 0, "", tmp_result?.get(position)?.name.toString(), 0)
                var tmp_output : JSONObject? = null
                tmp_output  = JSONObject("{}")
                tmp_output!!.put("name", snack.name)
                tmp_output!!.put("amountShow", "")
                tmp_output!!.put("amount", 0)
                tmp_output!!.put("cal", 0)
                tmp_output!!.put("feq", 0)
                nutriInfoBreakfast!!.put("snack", tmp_output)
                nutriInfo!!.put("breakfast", nutriInfoBreakfast)
                aiData!!.put("nutriInfo", nutriInfo)
            }
        }

        lunchSnackspinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val tmp_result = gv.getSnackInfo()
                var snack = Snack(0, 0, "", tmp_result?.get(position)?.name.toString(), 0)
                var tmp_output : JSONObject? = null
                tmp_output  = JSONObject("{}")
                tmp_output!!.put("name", snack.name)
                tmp_output!!.put("amountShow", "")
                tmp_output!!.put("amount", 0)
                tmp_output!!.put("cal", 0)
                tmp_output!!.put("feq", 0)
                nutriInfoLunch!!.put("snack", tmp_output)
                nutriInfo!!.put("lunch", nutriInfoLunch)
                aiData!!.put("nutriInfo", nutriInfo)
            }
        }

        dinnerSnackspinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val tmp_result = gv.getSnackInfo()
                var snack = Snack(0, 0, "", tmp_result?.get(position)?.name.toString(), 0)
                var tmp_output : JSONObject? = null
                tmp_output  = JSONObject("{}")
                tmp_output!!.put("name", snack.name)
                tmp_output!!.put("amountShow", "")
                tmp_output!!.put("amount", 0)
                tmp_output!!.put("cal", 0)
                tmp_output!!.put("feq", 0)
                nutriInfoDinner!!.put("snack", tmp_output)
                nutriInfo!!.put("dinner", nutriInfoDinner)
                aiData!!.put("nutriInfo", nutriInfo)
            }
        }

        snackSnackspinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val tmp_result = gv.getSnackInfo()
                var snack = Snack(0, 0, "", tmp_result?.get(position)?.name.toString(), 0)
                var tmp_output : JSONObject? = null
                tmp_output  = JSONObject("{}")
                tmp_output!!.put("name", snack.name)
                tmp_output!!.put("amountShow", "")
                tmp_output!!.put("amount", 0)
                tmp_output!!.put("cal", 0)
                tmp_output!!.put("feq", 0)
                nutriInfoSnack!!.put("snack", tmp_output)
                nutriInfo!!.put("snack", nutriInfoSnack)
                aiData!!.put("nutriInfo", nutriInfo)
            }
        }

        resultButton.setOnClickListener{
            presenter?.getPlan(aiData!!)
        }

//        Log.d("Breakfast[Grain]", grainList.toString())
//        Log.d("Breakfast[Meat]", meatList.toString())
//        Log.d("Breakfast[Vegetable]", vegetableList.toString())
//        Log.d("Breakfast[Fruit]", fruitList.toString())
//        Log.d("Breakfast[Milk]",milkList.toString())
//        Log.d("Breakfast[Fat]", fatList.toString())
//        Log.d("Breakfast[Snack]", snackList.toString())
    }

    fun getGrainName(grainInfo: List<DataItem>?): MutableList<String> {
        var output: MutableList<String> = mutableListOf()

        for (value in grainInfo!!){
            output.add(value.name)
        }

        global?.setGrainList(output)

        return output
    }

    fun getMeatName(meatInfo: List<DataItem>?): MutableList<String> {
        var output: MutableList<String> = mutableListOf()

        for (value in meatInfo!!){
            output.add(value.name)
        }

        global?.setMeatList(output)

        return output
    }

    fun getVegetableName(vegetableInfo: List<DataItem>?): MutableList<String> {
        var output: MutableList<String> = mutableListOf()

        for (value in vegetableInfo!!){
            output.add(value.name)
        }

        global?.setVegetableList(output)

        return output
    }

    fun getMilkName(milkInfo: List<DataItem>?): MutableList<String> {
        var output: MutableList<String> = mutableListOf()

        for (value in milkInfo!!){
            output.add(value.name)
        }

        global?.setMilkList(output)

        return output
    }

    fun getFatName(fatInfo: List<DataItem>?): MutableList<String> {
        var output: MutableList<String> = mutableListOf()

        for (value in fatInfo!!){
            output.add(value.name)
        }

        global?.setFatList(output)

        return output
    }

    fun getFruitName(fruitInfo: List<DataItem>?): MutableList<String> {
        var output: MutableList<String> = mutableListOf()

        for (value in fruitInfo!!){
            output.add(value.name)
        }

        global?.setFruitList(output)

        return output
    }

    fun getSnackName(snackInfo: List<DataItem>?): MutableList<String> {
        var output: MutableList<String> = mutableListOf()

        for (value in snackInfo!!){
            output.add(value.name)
        }

        global?.setSnackList(output)

        return output
    }

    fun initialize(){
        presenter = BreakfastPresenter(this)
    }

//    fun getFoodSubtypeAmount(foodInfo: DataItem?){
//        var output: MutableList<String> = mutableListOf()
//        var tmp : List<FoodAmountDataItem>
//        Log.d("[foodInfo]", foodInfo.toString())
//        val call = apiClient?.getFoodSubTypeAmount(foodInfo?.foodSubType!!)
//        call?.enqueue(object : Callback<FoodAmount>{
//            override fun onFailure(call: Call<FoodAmount>, t: Throwable) {
//                Log.d("failure", t.toString())
//            }
//
//            override fun onResponse(call: Call<FoodAmount>, response: Response<FoodAmount>) {
//                if(response.isSuccessful){
//                    var results = response.body()
//                    Log.d("BreakfastActivity", results.toString())
//                }
//            }
//        })
//
////        return output
//    }

}
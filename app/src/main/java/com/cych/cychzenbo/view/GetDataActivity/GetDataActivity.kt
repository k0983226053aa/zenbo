package com.cych.cychzenbo.view.GetDataActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.Toast

import com.cych.cychzenbo.R
import com.cych.cychzenbo.allinterface.GlobalVariable
import kotlinx.android.synthetic.main.activity_get_data.*

import com.cych.cychzenbo.presenter.GetDataPresenter.GetDataPresenter
import com.cych.cychzenbo.allinterface.getDataInterface.getDataInterface
import com.cych.cychzenbo.model.JSONData.BasicInfo.BasicInfo
import com.cych.cychzenbo.model.JSONData.FoodInfo.DataItem
import com.cych.cychzenbo.view.Breakfast.BreakfastActivity

class GetDataActivity : AppCompatActivity(), getDataInterface.getDataView {
    private var presenter: GetDataPresenter? = null
    private var global: GlobalVariable? = null

    override fun receiveData() {
        var data:BasicInfo? = presenter?.showBasicInfo()
        global?.setBasicInfo(data)
        startActivity(Intent(this, BreakfastActivity::class.java))
    }

    override fun receiveMilkInfo() {
        var data: List<DataItem>? = presenter?.showMilkInfo()
        global?.setMilkInfo(data)
    }

    override fun receiveGrainInfo() {
        var data: List<DataItem>? = presenter?.showGrainInfo()
        global?.setGrainInfo(data)
    }

    override fun receiveMeatInfo() {
        var data: List<DataItem>? = presenter?.showMeatInfo()
        global?.setMeatInfo(data)
    }

    override fun receiveVegetableInfo() {
        var data: List<DataItem>? = presenter?.showVegetableInfo()
        global?.setVegetableInfo(data)
    }

    override fun receiveFatInfo() {
        var data: List<DataItem>? = presenter?.showFatInfo()
        global?.setFatInfo(data)
    }

    override fun receiveFruitInfo() {
        var data: List<DataItem>? = presenter?.showFruitInfo()
        global?.setFruitInfo(data)
    }

    override fun receiveSnackInfo() {
        var data: List<DataItem>? = presenter?.showSnackInfo()
        global?.setSnackInfo(data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_data)

        var gv: GlobalVariable = applicationContext as GlobalVariable
        global = gv
        initialize()

        presenter?.getFoodInfo("乳品類")
        presenter?.getFoodInfo("全榖雜糧類")
        presenter?.getFoodInfo("豆魚蛋肉類")
        presenter?.getFoodInfo("蔬菜類")
        presenter?.getFoodInfo("水果類")
        presenter?.getFoodInfo("油脂與堅果種子類")
        presenter?.getFoodInfo("小吃")

        genderGroup.setOnCheckedChangeListener { group, checkedId ->
            var checkedRadioButton:RadioButton = group.findViewById(checkedId) as RadioButton
            var isChecked: Boolean = checkedRadioButton.isChecked()

            if(isChecked){
                Log.d("PA", gv.getPA().toString())
                var gender:String = checkedRadioButton.getText() as String
                if(gender == "男"){
                    gv.setGender("male")
                }
                else{
                    gv.setGender("female")
                }
            }
        }

        PAGroup.setOnCheckedChangeListener { group, checkedId ->
            var checkedRadioButton:RadioButton = group.findViewById(checkedId) as RadioButton
            var isChecked: Boolean = checkedRadioButton.isChecked()

            if(isChecked){
                Log.d("Gender", gv.getGender())
                var PA:String = checkedRadioButton.getText() as String
                if(PA == "低"){
                    gv.setPA(1.05F)
                }
                else if(PA == "稍低"){
                    gv.setPA(1.15F)
                }
                else if(PA == "適度"){
                    gv.setPA(1.25F)
                }
                else{
                    gv.setPA(1.35F)
                }
            }
        }

        disease0Check.setOnCheckedChangeListener { buttonView, isChecked ->

            if(isChecked){
                gv.setDisease("set", 1)
            }
            else if(!isChecked){
                gv.setDisease("clean", 1)
            }
        }

        disease1Check.setOnCheckedChangeListener { buttonView, isChecked ->

            if(isChecked){
                gv.setDisease("set", 2)
            }
            else if(!isChecked){
                gv.setDisease("clean", 2)
            }
        }

        disease2Check.setOnCheckedChangeListener { buttonView, isChecked ->

            if(isChecked){
                gv.setDisease("set", 4)
            }
            else if(!isChecked){
                gv.setDisease("clean", 4)
            }
        }

        basicButton.setOnClickListener {
            if(ageEditText.text.isEmpty()){
                Toast.makeText(this, "請輸入年齡", Toast.LENGTH_LONG).show()
            }
            else if(heightEditText.text.isEmpty()){
                Toast.makeText(this, "請輸入身高", Toast.LENGTH_LONG).show()
            }
            else if(weightEditText.text.isEmpty()){
                Toast.makeText(this, "請輸入體重", Toast.LENGTH_LONG).show()
            }
            else {
                gv.setAge(ageEditText.text.toString().toInt())
                gv.setHeight(heightEditText.text.toString().toInt())
                gv.setWeight(weightEditText.text.toString().toInt())
                presenter?.getBasicInfo(
                    ageEditText.text.toString().toInt(),
                    gv.getGender(),
                    heightEditText.text.toString().toInt(),
                    weightEditText.text.toString().toInt(),
                    gv.getPA(),
                    gv.getDisease()
                )
            }
        }
    }


    fun initialize(){
        presenter = GetDataPresenter(this)
    }
}
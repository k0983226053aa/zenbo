package com.cych.cychzenbo.view.ShowResultActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cych.cychzenbo.R
import com.cych.cychzenbo.allinterface.GlobalVariable
import kotlinx.android.synthetic.main.activity_show_result.*

class ShowResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_result)

        var gv: GlobalVariable = applicationContext as GlobalVariable

        planShowView.text = gv.getPlanJSON().toString()
        breakfastShowView.text = gv.getBasicInfo()?.breakfast.toString()
        lunchShowView.text = gv.getBasicInfo()?.lunch.toString()
        dinnerShowView.text = gv.getBasicInfo()?.dinner.toString()
        snackShowView.text = gv.getBasicInfo()?.snack.toString()
    }
}
package com.cych.cychzenbo.view.MainActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import android.os.Bundle
import com.cych.cychzenbo.R

import com.cych.cychzenbo.allinterface.MainInterface.MainInterface
import com.cych.cychzenbo.presenter.MainPresenter.MainPresenter
import com.cych.cychzenbo.view.GetDataActivity.GetDataActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext


class MainActivity : AppCompatActivity(), MainInterface.mainView{

    private var presenter: MainPresenter? = null

    override fun receiveStatus() {
        val status = presenter?.showLoginStatus().toString()
//        Log.d("receiveStatus", presenter?.showLoginStatus().toString())

        if (status == "true"){
            startActivity(Intent(this, GetDataActivity::class.java))
        }
        else{
            Toast.makeText(this, status, Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize()

        loginButton.setOnClickListener{
            presenter?.loginAPI(loginAccountEditText.text.toString(), loginPasswdEditText.text.toString())
        }
    }

    override fun onPause() {
        super.onPause()
    }

    fun initialize() {
        presenter = MainPresenter(this)
    }
}
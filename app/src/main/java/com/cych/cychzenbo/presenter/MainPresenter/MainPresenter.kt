package com.cych.cychzenbo.presenter.MainPresenter

import android.util.Log
import com.cych.cychzenbo.allinterface.MainInterface.MainInterface
import com.cych.cychzenbo.model.MainModel.MainModel
import kotlinx.android.synthetic.main.activity_main.*

class MainPresenter(mainView: MainInterface.mainView) : MainInterface.mainPresenter {
    private val view: MainInterface.mainView = mainView
    private val model: MainInterface.mainModel = MainModel()

    override fun loginAPI(email: String, passwd: String) {
        model?.getUser(email, passwd, this)
    }

    override fun showLoginStatus() = model.getLoginStatus()

    override fun deliveryStatus() {
        view.receiveStatus()
    }
}
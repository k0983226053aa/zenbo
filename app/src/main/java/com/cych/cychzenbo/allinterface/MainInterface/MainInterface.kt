package com.cych.cychzenbo.allinterface.MainInterface

interface MainInterface {
    interface mainModel{
        fun getLoginStatus():String
        fun getUser(email:String, passwd:String, presenter: mainPresenter)
    }

    interface mainView{
        fun receiveStatus()
    }

    interface mainPresenter {
        fun showLoginStatus():String
        fun loginAPI(email:String, passwd:String)
        fun deliveryStatus()
    }

}
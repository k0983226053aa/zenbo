package com.cych.cychzenbo.model.API

import com.cych.cychzenbo.allinterface.Setting.Setting
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestAPI {
    companion object {
        private var retrofit: Retrofit? = null

        val client: Retrofit
            get() {
                if (retrofit == null){
                    retrofit = Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(Setting.BASE_URL)
                        .build()
                }
                return retrofit!!
            }
    }
}
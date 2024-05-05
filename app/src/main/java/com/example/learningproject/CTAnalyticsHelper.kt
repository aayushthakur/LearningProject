package com.example.learningproject

import com.clevertap.android.sdk.CleverTapAPI


class CTAnalyticsHelper private constructor() {

    companion object {
        val INSTANCE: CTAnalyticsHelper by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { CTAnalyticsHelper() }
    }

    fun onUserLogin(map : HashMap<String, Any>){
        MyApplication.INSTANCE.cleverTapInstance()?.onUserLogin(map)
    }

    fun pushProfile(map : HashMap<String, Any>){
        MyApplication.INSTANCE.cleverTapInstance()?.pushProfile(map)
    }

    fun pushEvent(event : String, map: HashMap<String, Any> = HashMap() ){
        MyApplication.INSTANCE.cleverTapInstance()?.pushEvent(event, map)
    }

    fun pushChargedEvent(chargeDetails : HashMap<String,Any>, items: ArrayList<HashMap<String, Any>> = ArrayList() ){
        MyApplication.INSTANCE.cleverTapInstance()?.pushChargedEvent(chargeDetails, items)
    }
}
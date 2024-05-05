package com.example.learningproject

import android.app.Application
import com.clevertap.android.sdk.ActivityLifecycleCallback
import com.clevertap.android.sdk.CleverTapAPI

class MyApplication : Application() {

    companion object Factory {
        private var INSTANCE: MyApplication? = null
        fun getInstance() : MyApplication {
            return INSTANCE!!
        }
    }
    private var clevertap : CleverTapAPI? = null

    override fun onCreate() {
        ActivityLifecycleCallback.register(this);
        super.onCreate()
        INSTANCE = this
        clevertap = CleverTapAPI.getDefaultInstance(applicationContext)!!
        clevertap!!.enableDeviceNetworkInfoReporting(true)
        CleverTapAPI.setDebugLevel(CleverTapAPI.LogLevel.VERBOSE)
    }


    fun clevertap() : CleverTapAPI? {
        return clevertap
    }
}
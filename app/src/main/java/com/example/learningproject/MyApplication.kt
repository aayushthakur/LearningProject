package com.example.learningproject

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.clevertap.android.sdk.ActivityLifecycleCallback
import com.clevertap.android.sdk.CleverTapAPI

class MyApplication : Application() {

    companion object {
        val INSTANCE: MyApplication by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { MyApplication() }
    }

    private var clevertap : CleverTapAPI ? = null

    override fun onCreate() {
        ActivityLifecycleCallback.register(this);
        super.onCreate()
        clevertap = CleverTapAPI.getDefaultInstance(INSTANCE)
        clevertap?.enableDeviceNetworkInfoReporting(true)
        CleverTapAPI.setDebugLevel(CleverTapAPI.LogLevel.VERBOSE)
    }

    fun cleverTapInstance() : CleverTapAPI?{
        return clevertap
    }
}
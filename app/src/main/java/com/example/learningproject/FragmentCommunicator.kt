package com.example.learningproject

import com.clevertap.android.sdk.displayunits.model.CleverTapDisplayUnit


interface FragmentCommunicator {

    fun loadData(units: ArrayList<CleverTapDisplayUnit>)

}
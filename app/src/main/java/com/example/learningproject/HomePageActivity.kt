package com.example.learningproject

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.clevertap.android.sdk.CleverTapAPI
import com.clevertap.android.sdk.displayunits.DisplayUnitListener
import com.clevertap.android.sdk.displayunits.model.CleverTapDisplayUnit
import com.example.learningproject.databinding.ActivityHomePageBinding
import com.example.learningproject.fragments.AccountFragment
import com.example.learningproject.fragments.CartFragment
import com.example.learningproject.fragments.FavouriteFragment
import com.example.learningproject.fragments.HomeFragment


class HomePageActivity : AppCompatActivity(), DisplayUnitListener {

    private lateinit var binding: ActivityHomePageBinding
    private val TAG: String = HomePageActivity::class.java.getSimpleName()
    var mListener: FragmentCommunicator? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CTAnalyticsHelper.INSTANCE.pushEvent("ND Carousel Banner")
        MyApplication.getInstance().clevertap().apply {
            this!!.setDisplayUnitListener(this@HomePageActivity)
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home_page)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    loadFragment(HomeFragment.newInstance())
                }

                R.id.cart -> {
                    loadFragment(CartFragment.newInstance())
                }

                R.id.fav -> {
                    loadFragment(FavouriteFragment.newInstance())
                }

                R.id.profile -> {
                    loadFragment(AccountFragment.newInstance())
                }
            }
            true
        }

        //setting default fragment as Home
        loadFragment(HomeFragment.newInstance())

    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(binding.frameLayout.id, fragment)
        transaction.commit()
    }

    fun setFragmentListener(fragmentCommunicator: FragmentCommunicator) {
        mListener = fragmentCommunicator
    }

    override fun onDisplayUnitsLoaded(units: ArrayList<CleverTapDisplayUnit>?) {
        Log.d(TAG, "onDisplayUnitsLoaded() called with: units = [" + units.toString() + "]");
        mListener?.loadData(units!!)
    }

}
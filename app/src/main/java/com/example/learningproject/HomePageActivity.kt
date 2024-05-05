package com.example.learningproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.learningproject.databinding.ActivityHomePageBinding
import com.example.learningproject.fragments.AccountFragment
import com.example.learningproject.fragments.CartFragment
import com.example.learningproject.fragments.FavouriteFragment
import com.example.learningproject.fragments.HomeFragment

class HomePageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomePageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
}
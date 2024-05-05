package com.example.learningproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.learningproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            loginbutton.setOnClickListener { loginButtonClicked() }
            signupbutton.setOnClickListener { signUpButtonClicked() }
        }

    }

    private fun loginButtonClicked(){
        val email = binding.edtEmailPhone.editText?.text.toString()
        val password = binding.edtPassword.text.toString()
        val data: HashMap<String, Any> = hashMapOf("Email" to email, "Identity" to password)
        CTAnalyticsHelper.INSTANCE.onUserLogin(data)
        Toast.makeText(applicationContext,"Login Successful", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, HomePageActivity::class.java)
        startActivity(intent)
    }

    private fun signUpButtonClicked(){

    }

}
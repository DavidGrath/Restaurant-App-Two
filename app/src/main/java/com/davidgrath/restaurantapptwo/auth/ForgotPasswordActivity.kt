package com.davidgrath.restaurantapptwo.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.davidgrath.restaurantapptwo.databinding.ActivityForgotPasswordBinding

class ForgotPasswordActivity : AppCompatActivity() {

    lateinit var binding : ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setSupportActionBar(binding.toolbarRestaurantDetails)
        supportActionBar!!.title = null
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        binding.toolbarRestaurantDetails.setNavigationOnClickListener {
            finish()
        }
        setContentView(binding.root)

    }
}
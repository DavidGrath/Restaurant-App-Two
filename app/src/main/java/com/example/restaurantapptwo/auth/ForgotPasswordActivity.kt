package com.example.restaurantapptwo.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.restaurantapptwo.databinding.ActivityForgotPasswordBinding

class ForgotPasswordActivity : AppCompatActivity() {

    lateinit var binding : ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
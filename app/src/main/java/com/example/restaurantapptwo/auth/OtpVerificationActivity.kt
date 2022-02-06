package com.example.restaurantapptwo.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.restaurantapptwo.databinding.ActivityOtpVerificationBinding

class OtpVerificationActivity : AppCompatActivity() {

    lateinit var binding : ActivityOtpVerificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
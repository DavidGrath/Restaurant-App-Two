package com.davidgrath.restaurantapptwo.auth

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.davidgrath.restaurantapptwo.databinding.ActivityOtpVerificationBinding

class OtpVerificationActivity : AppCompatActivity() {

    lateinit var binding : ActivityOtpVerificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpVerificationBinding.inflate(layoutInflater)
        binding.otpEditTextOtpVerification.setOnCompleteListener {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
        setContentView(binding.root)
    }
}
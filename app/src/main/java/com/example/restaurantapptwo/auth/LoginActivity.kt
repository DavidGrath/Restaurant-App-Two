package com.example.restaurantapptwo.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.restaurantapptwo.main.MainActivity
import com.example.restaurantapptwo.databinding.ActivityLoginBinding
import com.example.restaurantapptwo.onboarding.LocationSelectActivity

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonLoginLogin.setOnClickListener(this)
        binding.buttonLoginFacebookConnect.setOnClickListener(this)
        binding.textviewLoginCreateAccount.setOnClickListener(this)
        binding.textviewLoginForgotPassword.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        v?.let {
            when(it) {
                binding.buttonLoginLogin, binding.buttonLoginFacebookConnect -> {
                    val hasLocation = false
                    if(hasLocation) {
                        val mainIntent = Intent(this, MainActivity::class.java)
                        startActivity(mainIntent)
                        finish()
                    } else {
                        val selectLocationIntent = Intent(this, LocationSelectActivity::class.java)
                        startActivity(selectLocationIntent)
                        finish()
                    }
                }
                binding.textviewLoginCreateAccount -> {
                    val createAccountIntent = Intent(this, CreateAccountActivity::class.java)
                    //TODO Flags
                    startActivity(createAccountIntent)
                }
                binding.textviewLoginForgotPassword -> {
                    val forgotPasswordIntent = Intent(this, ForgotPasswordActivity::class.java)
                    startActivity(forgotPasswordIntent)
                }
            }
        }
    }
}
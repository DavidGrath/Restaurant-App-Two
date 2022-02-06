package com.example.restaurantapptwo.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.restaurantapptwo.main.MainActivity
import com.example.restaurantapptwo.databinding.ActivityCreateAccountBinding
import com.example.restaurantapptwo.onboarding.LocationSelectActivity

class CreateAccountActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding : ActivityCreateAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonCreateAccountCreate.setOnClickListener(this)
        //TODO ClickableSpan (and possibly localization for the indices)
        binding.textviewCreateAccountLogin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        v?.let {
            when(it) {
                binding.buttonCreateAccountCreate -> {
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
                binding.textviewCreateAccountLogin -> {
                    val loginIntent = Intent(this, LoginActivity::class.java)
                    //TODO Research flags. Can't remember
//                    loginIntent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
                    startActivity(loginIntent)
                }
            }
        }
    }
}
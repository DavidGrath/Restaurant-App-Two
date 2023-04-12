package com.davidgrath.restaurantapptwo.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.davidgrath.restaurantapptwo.Constants
import com.davidgrath.restaurantapptwo.application.RestaurantAppTwo
import com.davidgrath.restaurantapptwo.auth.CreateAccountActivity
import com.davidgrath.restaurantapptwo.auth.OtpVerificationActivity
import com.davidgrath.restaurantapptwo.location.LocationSelectActivity
import com.davidgrath.restaurantapptwo.onboarding.OnboardingActivity

class SplashScreenActivity : AppCompatActivity() {

    lateinit var viewModel : SplashScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this,
            SplashScreenViewModelFactory((application as RestaurantAppTwo).getAuthUseCase())).get(SplashScreenViewModel::class.java)
        val preferences = getSharedPreferences(Constants.APPLICATION_NAME, MODE_PRIVATE)

        val completedOnboarding = preferences.getBoolean(Constants.PreferencesTitles.ONBOARDING_COMPLETED, false)

        if (completedOnboarding) {
            if (viewModel.isLoggedIn()) {
                if (viewModel.isLocationSet()) {
                    startActivity(Intent(this, MainActivity::class.java))
                } else {
                    startActivity(Intent(this, LocationSelectActivity::class.java))
                }
            } else {
                val intent = Intent(this, CreateAccountActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
        } else {
            startActivity(Intent(this, OnboardingActivity::class.java))
        }

        finish()
    }
}
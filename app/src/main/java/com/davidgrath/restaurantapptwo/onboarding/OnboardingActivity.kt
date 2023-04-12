package com.davidgrath.restaurantapptwo.onboarding

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.ViewModelProvider
import com.davidgrath.restaurantapptwo.Constants
import com.davidgrath.restaurantapptwo.R
import com.davidgrath.restaurantapptwo.application.RestaurantAppTwo
import com.davidgrath.restaurantapptwo.auth.CreateAccountActivity
import com.davidgrath.restaurantapptwo.databinding.ActivityOnboardingBinding
import com.davidgrath.restaurantapptwo.location.LocationSelectActivity
import com.davidgrath.restaurantapptwo.main.MainActivity

class OnboardingActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityOnboardingBinding
    private lateinit var viewModel: OnboardingViewModel
    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = resources.getColor(R.color.white)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true
        viewModel = ViewModelProvider(this,
            OnboardingViewModelFactory((application as RestaurantAppTwo).getAuthUseCase())).get(OnboardingViewModel::class.java)
        val fragments = arrayOf(
            OnboardingFragmentDiscover.newInstance(),
            OnboardingFragmentChooseDish.newInstance(),
            OnboardingFragmentDelivery.newInstance()
        )
        val viewPagerAdapter = OnboardingViewPagerAdapter(this, fragments)
        binding.viewPagerOnboarding.adapter = viewPagerAdapter
        binding.textviewOnboardingNextSkip.setOnClickListener(this)
        preferences = getSharedPreferences(com.davidgrath.restaurantapptwo.Constants.APPLICATION_NAME, MODE_PRIVATE)
    }

    override fun onClick(v: View?) {
        v?.let {
            when(it) {
                binding.textviewOnboardingNextSkip -> {
                    if(binding.viewPagerOnboarding.currentItem == 2) {
                        //TODO Check if adapter has listener and use it
                        finishOnboarding()
                    } else {
                        binding.viewPagerOnboarding.currentItem++
                    }
                }
                else -> {

                }
            }
        }
    }

    private fun finishOnboarding() {
        preferences.edit()
            .putBoolean(Constants.PreferencesTitles.ONBOARDING_COMPLETED, true)
            .apply()
        if (viewModel.isLoggedIn()) {
            if(viewModel.isLocationSet()) {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                startActivity(Intent(this, LocationSelectActivity::class.java))
            }

        } else {
            val intent = Intent(this, CreateAccountActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
        finish()
    }
}
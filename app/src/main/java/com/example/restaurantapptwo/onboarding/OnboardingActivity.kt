package com.example.restaurantapptwo.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.restaurantapptwo.main.MainActivity
import com.example.restaurantapptwo.auth.CreateAccountActivity
import com.example.restaurantapptwo.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding : ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val fragments = arrayOf(
            OnboardingFragmentDiscover.newInstance(),
            OnboardingFragmentChooseDish.newInstance(),
            OnboardingFragmentDelivery.newInstance()
        )
        val viewPagerAdapter = OnboardingViewPagerAdapter(this, fragments)
        binding.viewPagerOnboarding.adapter = viewPagerAdapter
        binding.textviewOnboardingNextSkip.setOnClickListener(this)

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
        //TODO Implement auth
        val authDetailsPresent = false
        val activityClass = if (authDetailsPresent) MainActivity::class.java else CreateAccountActivity::class.java
        val intent = Intent(this, activityClass)
        startActivity(intent)
        finish()
    }
}
package com.example.restaurantapptwo.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.restaurantapptwo.R

class OnboardingFragmentChooseDish : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_onboarding_choose_dish, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(): OnboardingFragmentChooseDish {
            return OnboardingFragmentChooseDish().apply {
            }
        }
    }
}
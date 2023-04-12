package com.davidgrath.restaurantapptwo.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.davidgrath.restaurantapptwo.R

class OnboardingFragmentDelivery : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_onboarding_delivery, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(): OnboardingFragmentDelivery {
            return OnboardingFragmentDelivery().apply {
            }
        }
    }
}
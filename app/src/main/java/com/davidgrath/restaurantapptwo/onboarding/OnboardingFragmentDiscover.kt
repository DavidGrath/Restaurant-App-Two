package com.davidgrath.restaurantapptwo.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.davidgrath.restaurantapptwo.R

class OnboardingFragmentDiscover : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_onboarding_discover, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(): OnboardingFragmentDiscover {
            return OnboardingFragmentDiscover().apply {
            }
        }
    }
}
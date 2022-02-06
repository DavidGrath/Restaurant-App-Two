package com.example.restaurantapptwo.onboarding

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.restaurantapptwo.databinding.FragmentLocationPromptBinding

class LocationPromptFragment : Fragment(), View.OnClickListener {

    lateinit var binding : FragmentLocationPromptBinding

    interface LocationPromptFragmentListener {
        fun onUseCurrentLocation()
        fun onSelectLocationManually()
    }
    private var listener : LocationPromptFragmentListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is LocationPromptFragmentListener) {
            listener = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLocationPromptBinding.inflate(inflater, container, false)
        binding.buttonLocationPromptUseCurrentLocation.setOnClickListener(this)
        binding.textviewLocationPromptManualSelect.setOnClickListener(this)
        return binding.root
    }

    override fun onClick(v: View?) {
        v?.let {
            when(it) {
                binding.buttonLocationPromptUseCurrentLocation -> {
                    listener?.onUseCurrentLocation()
                }
                binding.textviewLocationPromptManualSelect -> {
                    listener?.onSelectLocationManually()
                }
                else -> {

                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(): LocationPromptFragment {
            return LocationPromptFragment().apply {
            }
        }
    }
}
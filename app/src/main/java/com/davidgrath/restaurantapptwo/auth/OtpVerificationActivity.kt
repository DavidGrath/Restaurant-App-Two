package com.davidgrath.restaurantapptwo.auth

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.davidgrath.restaurantapptwo.auth.viewmodels.OtpVerificationViewModel
import com.davidgrath.restaurantapptwo.databinding.ActivityOtpVerificationBinding

class OtpVerificationActivity : AppCompatActivity(), OnClickListener {

    lateinit var binding : ActivityOtpVerificationBinding
    lateinit var viewModel: OtpVerificationViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpVerificationBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(OtpVerificationViewModel::class.java)
        binding.otpEditTextOtpVerification.setOnCompleteListener {
            //Runtime exception if this isn't set
        }
        binding.otpEditTextOtpVerification.addTextChangedListener {
            viewModel.otp = it!!.toString()
            binding.buttonOtpVerificationVerify.isEnabled = it.length == 4
        }
        if(savedInstanceState != null) {
            binding.otpEditTextOtpVerification.setText(viewModel.otp)
        }
        binding.buttonOtpVerificationVerify.isEnabled = binding.otpEditTextOtpVerification.otpValue?.length == 4
        binding.buttonOtpVerificationVerify.setOnClickListener(this)
        setContentView(binding.root)
    }

    override fun onClick(v: View?) {
        v?.let {
            when(it) {
                binding.buttonOtpVerificationVerify -> {
                    val otp = binding.otpEditTextOtpVerification.otpValue
                    if(otp != null) {
                        if(otp.length == 4) {
                            //Proceed
                        } else {

                        }
                    }
                }
            }
        }
    }
}
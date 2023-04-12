package com.davidgrath.restaurantapptwo.auth

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.transition.Transition
import com.davidgrath.restaurantapptwo.R
import com.davidgrath.restaurantapptwo.application.RestaurantAppTwo
import com.davidgrath.restaurantapptwo.auth.viewmodels.CreateAccountViewModel
import com.davidgrath.restaurantapptwo.auth.viewmodels.factories.CreateAccountViewModelFactory
import com.davidgrath.restaurantapptwo.databinding.ActivityCreateAccountBinding
import com.davidgrath.restaurantapptwo.location.LocationSelectActivity
import com.davidgrath.restaurantapptwo.main.AsyncResult
import com.davidgrath.restaurantapptwo.main.MainActivity


class CreateAccountActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding : ActivityCreateAccountBinding
    lateinit var viewModel : CreateAccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Glide.with(this)
            .asDrawable()
            .placeholder(ColorDrawable(Color.DKGRAY))
            .centerCrop()
            .load(R.raw.create_account_background)
            .into(object : CustomViewTarget<LinearLayout, Drawable>(binding.root as LinearLayout) {
                override fun onLoadFailed(errorDrawable: Drawable?) {

                }

                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                    resource.colorFilter = PorterDuffColorFilter(Color.DKGRAY, PorterDuff.Mode.MULTIPLY)
                    view.background = resource
                }

                override fun onResourceCleared(placeholder: Drawable?) {

                }
            })
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.statusBarColor = Color.TRANSPARENT
        viewModel = CreateAccountViewModelFactory((application as RestaurantAppTwo).getAuthUseCase()).create(CreateAccountViewModel::class.java)

        binding.createAccountForm = viewModel.createAccountFormUI
        binding.buttonCreateAccountCreate.setOnClickListener(this)
        binding.textviewCreateAccountLogin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        v?.let {
            when(it) {
                binding.buttonCreateAccountCreate -> {
                    if(validatePassword()) {
                        binding.buttonCreateAccountCreate.isEnabled = false
                        viewModel.signUp().observe(this) {
                            when(it) {
                                is AsyncResult.Success -> {
                                    if (viewModel.isLocationSet()) {
                                        val mainIntent = Intent(this, MainActivity::class.java)
                                        startActivity(mainIntent)
                                        finish()
                                    } else {
                                        val selectLocationIntent =
                                            Intent(this, LocationSelectActivity::class.java)
                                        startActivity(selectLocationIntent)
                                        finish()
                                    }
                                }
                                is AsyncResult.Processing -> {
                                    binding.buttonCreateAccountCreate.isEnabled = false
                                }
                                is AsyncResult.Failure -> {
                                    binding.buttonCreateAccountCreate.isEnabled = true
                                    viewModel.createAccountFormUI.error = it.errorMessage
                                }
                            }
                        }

                    }
                }
                binding.textviewCreateAccountLogin -> {
                    val loginIntent = Intent(this, LoginActivity::class.java)
                    loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(loginIntent)
                }
            }
        }
    }

    private fun validatePassword() : Boolean {
        val form = viewModel.createAccountFormUI
        if(form.fullName.isEmpty()) {
            form.error = "Name must not be empty!"
            return false
        }
        if(form.email.isEmpty()) {
            form.error = "Email must not be empty!"
            return false
        }
        if(form.password.isEmpty()) {
            form.error = "Password must not be empty!"
            return false
        }
        if(form.password.length < 8) {
            form.error = "Password must be at least 8 characters!"
            return false
        }
        if(form.passwordConfirm != form.password) {
            form.error = "Passwords do not match!"
            return false
        }
        form.error = null
        return true
    }
}
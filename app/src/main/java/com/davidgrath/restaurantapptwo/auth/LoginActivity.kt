package com.davidgrath.restaurantapptwo.auth

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.transition.Transition
import com.davidgrath.restaurantapptwo.R
import com.davidgrath.restaurantapptwo.application.RestaurantAppTwo
import com.davidgrath.restaurantapptwo.auth.viewmodels.LoginViewModel
import com.davidgrath.restaurantapptwo.auth.viewmodels.factories.LoginViewModelFactory
import com.davidgrath.restaurantapptwo.databinding.ActivityLoginBinding
import com.davidgrath.restaurantapptwo.location.LocationSelectActivity
import com.davidgrath.restaurantapptwo.main.AsyncResult
import com.davidgrath.restaurantapptwo.main.MainActivity

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding : ActivityLoginBinding
    lateinit var viewModel : LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Glide.with(this)
            .asDrawable()
            .placeholder(ColorDrawable(Color.DKGRAY))
            .centerCrop()
            .load(R.raw.sample_food_5)
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
        viewModel = LoginViewModelFactory((application as RestaurantAppTwo).getAuthUseCase()).create(LoginViewModel::class.java)
        binding.loginForm = viewModel.loginFormUI
        binding.buttonLoginLogin.setOnClickListener(this)
        binding.buttonLoginFacebookConnect.setOnClickListener(this)
        binding.textviewLoginCreateAccount.setOnClickListener(this)
        binding.textviewLoginForgotPassword.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        v?.let {
            when(it) {
                binding.buttonLoginLogin -> {
                    if(validatePassword()) {
                        binding.buttonLoginLogin.isEnabled = false
                        viewModel.login().observe(this) {
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
                                    binding.buttonLoginLogin.isEnabled = false
                                }
                                is AsyncResult.Failure -> {
                                    binding.buttonLoginLogin.isEnabled = true
                                    viewModel.loginFormUI.error = it.errorMessage
                                }
                            }
                        }

                    }
                }
                binding.buttonLoginFacebookConnect -> {
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
                binding.textviewLoginCreateAccount -> {
                    val createAccountIntent = Intent(this, CreateAccountActivity::class.java)
                    createAccountIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(createAccountIntent)
                    finish()
                }
                binding.textviewLoginForgotPassword -> {
                    val forgotPasswordIntent = Intent(this, ForgotPasswordActivity::class.java)
                    startActivity(forgotPasswordIntent)
                }
            }
        }
    }

    private fun validatePassword() : Boolean {
        val form = viewModel.loginFormUI
        if(form.username.isEmpty()) {
            form.error = "Username must not be empty!"
            Log.d("Login", "Empty username")
            return false
        }
        if(form.password.length < 8) {
            form.error = "Password must be at least 8 characters!"
            Log.d("Login", "Invalid password")
            return false
        }
        form.error = null
        return true
    }

    fun login() {

    }
}
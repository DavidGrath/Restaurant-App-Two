package com.davidgrath.restaurantapptwo.cart

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.ViewModelProvider
import com.davidgrath.restaurantapptwo.R
import com.davidgrath.restaurantapptwo.application.RestaurantAppTwo
import com.davidgrath.restaurantapptwo.cart.viewmodels.CheckoutViewModel
import com.davidgrath.restaurantapptwo.cart.viewmodels.factories.CheckoutViewModelFactory
import com.davidgrath.restaurantapptwo.databinding.ActivityCheckoutBinding
import com.davidgrath.restaurantapptwo.main.AsyncResult
import com.davidgrath.restaurantapptwo.main.MainActivity

class CheckoutActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding : ActivityCheckoutBinding
    lateinit var viewModel : CheckoutViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        binding.toolbarCheckout.setNavigationOnClickListener {
            finish()
        }
        setContentView(binding.root)
        window.statusBarColor = resources.getColor(R.color.light_grey)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true
        viewModel =
            ViewModelProvider(this,
                CheckoutViewModelFactory((application as RestaurantAppTwo).getCartUseCase())
            )
                .get(CheckoutViewModel::class.java)
        binding.checkoutForm = viewModel.checkoutFormUI
        binding.buttonCheckoutCheckout.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        v?.let {
            when(it) {
                binding.buttonCheckoutCheckout -> {
                    if(validateForm()) {
                        binding.buttonCheckoutCheckout.isEnabled = false
                        viewModel.checkout().observe(this) { result ->
                            when(result) {
                                is AsyncResult.Failure -> {
                                    viewModel.checkoutFormUI.error = result.errorMessage
                                    binding.buttonCheckoutCheckout.isEnabled = true
                                }
                                is AsyncResult.Processing -> {
                                    binding.buttonCheckoutCheckout.isEnabled = false
                                }
                                is AsyncResult.Success -> {
                                    val mainIntent = Intent(this, MainActivity::class.java)
                                    startActivity(mainIntent)
                                    finish()
                                }
                            }
                        }
                    }
                }
                else -> {

                }
            }
        }
    }

    private fun validateForm() : Boolean {
        val form = viewModel.checkoutFormUI
        if(form.deliveryAddress.isEmpty()) {
            form.error = "Delivery address cannot be empty!"
            return false
        }
        if(form.cardNumber.length < 16) {
            form.error = "Card Number not valid!"
            return false
        }
        if(form.cardHolderName.isEmpty() ) {
            form.error = "Card holder name cannot be empty!"
            return false
        }
        if(form.expiryDate.isEmpty()) {
            form.error = "Expiry date cannot be empty!"
            return false
        }
        if(form.cvv.isEmpty()) {
            form.error = "CVV cannot be empty!"
            return false
        }
        return true
    }
}
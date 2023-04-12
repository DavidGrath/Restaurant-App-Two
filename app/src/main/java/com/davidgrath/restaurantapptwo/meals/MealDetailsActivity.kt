package com.davidgrath.restaurantapptwo.meals

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.ImageViewTarget
import com.davidgrath.restaurantapptwo.Constants
import com.davidgrath.restaurantapptwo.R
import com.davidgrath.restaurantapptwo.application.RestaurantAppTwo
import com.davidgrath.restaurantapptwo.databinding.ActivityMealDetailsBinding
import com.davidgrath.restaurantapptwo.meals.adapters.MealExtrasRecyclerAdapter
import com.davidgrath.restaurantapptwo.meals.viewmodels.MealDetailsViewModel
import com.davidgrath.restaurantapptwo.meals.viewmodels.factories.MealDetailsViewModelFactory

class MealDetailsActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding : ActivityMealDetailsBinding
    lateinit var viewModel : MealDetailsViewModel
    lateinit var adapter: MealExtrasRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this,
            MealDetailsViewModelFactory((application as RestaurantAppTwo).getRestaurantUseCase())
        )
            .get(MealDetailsViewModel::class.java)
        window.statusBarColor = Color.TRANSPARENT
        Glide.with(this)
            .asDrawable()
            .placeholder(ColorDrawable(Color.DKGRAY))
            .centerCrop()
            .load(Constants.FOOD_IMAGE_IDS.random())
            .into(object : ImageViewTarget<Drawable>(binding.imageviewMealDetailsAppBar) {
                override fun setResource(resource: Drawable?) {
                    resource?.let {
                        view.setImageDrawable(resource)
                    }
                }
            })
        val mealDetails = viewModel.getMealDetails()
        setSupportActionBar(binding.toolbarMealDetails)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.toolbarMealDetails.title = mealDetails.name
        binding.textviewMealDetailsDescriptionText.text = mealDetails.description
        adapter = MealExtrasRecyclerAdapter(mealDetails.extras.toList())
        val layoutManager = LinearLayoutManager(this)
        binding.listviewMealDetailsExtras.adapter = adapter
        binding.listviewMealDetailsExtras.layoutManager = layoutManager
        binding.buttonMealDetailsAddQuantity.setOnClickListener(this)
        binding.buttonMealDetailsReduceQuantity.setOnClickListener(this)
        viewModel.orderQuantityLiveData.observe(this) { newQuantity ->
            //TODO Pluralisation
            if(newQuantity == 1) {
                binding.textviewMealDetailsQuantityCount.text = "${newQuantity} item"
            } else {
                binding.textviewMealDetailsQuantityCount.text = "${newQuantity} items"
            }
        }
        viewModel.orderTotalLiveData.observe(this) { newTotal ->
            binding.textviewMealDetailsTotal.text = "\u20a6$newTotal.00"
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_meal_details, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val favoriteItem = menu!!.findItem(R.id.menuitem_meal_details_favorite)
        val isFavorite = viewModel.getMealDetails().bookmarked
        if(isFavorite) {
            favoriteItem.icon = resources.getDrawable(R.drawable.ic_baseline_star_24)
        } else {
            favoriteItem.icon = resources.getDrawable(R.drawable.ic_baseline_star_outline_24)
        }
        return true
    }

    override fun onClick(v: View?) {
        v?.let {
            when(it) {
                binding.buttonMealDetailsAddQuantity -> {
                    viewModel.incrementQuantity()
                }
                binding.buttonMealDetailsReduceQuantity -> {
                    viewModel.decrementQuantity()
                }
            }
        }
    }
}
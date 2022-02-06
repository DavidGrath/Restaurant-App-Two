package com.example.restaurantapptwo.meals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurantapptwo.R
import com.example.restaurantapptwo.application.RestaurantAppTwo
import com.example.restaurantapptwo.databinding.ActivityMealDetailsBinding
import com.example.restaurantapptwo.meals.adapters.MealExtrasRecyclerAdapter
import com.example.restaurantapptwo.meals.viewmodels.MealDetailsViewModel
import com.example.restaurantapptwo.meals.viewmodels.factories.MealDetailsViewModelFactory

class MealDetailsActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding : ActivityMealDetailsBinding
    lateinit var viewModel : MealDetailsViewModel
    lateinit var adapter: MealExtrasRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this,
            MealDetailsViewModelFactory((application as RestaurantAppTwo).getRestaurantUseCase()))
            .get(MealDetailsViewModel::class.java)
        val mealDetails = viewModel.getMealDetails()
        binding.toolbarMealDetails.title = mealDetails.name
        binding.textviewMealDetailsDescriptionText.text = mealDetails.description
        adapter = MealExtrasRecyclerAdapter(mealDetails.extras.toList())
        val layoutManager = LinearLayoutManager(this)
        binding.listviewMealDetailsExtras.adapter = adapter
        binding.listviewMealDetailsExtras.layoutManager = layoutManager
        binding.buttonMealDetailsAddQuantity.setOnClickListener(this)
        binding.buttonMealDetailsReduceQuantity.setOnClickListener(this)
        binding.textviewMealDetailsQuantityCount.text = "${viewModel.orderQuantity}"
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
                    //TODO LiveData
                    viewModel.incrementQuantity()
                    binding.textviewMealDetailsQuantityCount.text = "${viewModel.orderQuantity}"
                }
                binding.buttonMealDetailsReduceQuantity -> {
                    //TODO LiveData
                    viewModel.decrementQuantity()
                    binding.textviewMealDetailsQuantityCount.text = "${viewModel.orderQuantity}"
                }
            }
        }
    }
}
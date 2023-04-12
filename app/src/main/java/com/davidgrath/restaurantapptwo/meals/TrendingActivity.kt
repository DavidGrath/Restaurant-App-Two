package com.davidgrath.restaurantapptwo.meals

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.davidgrath.restaurantapptwo.R
import com.davidgrath.restaurantapptwo.databinding.ActivityTrendingBinding
import com.davidgrath.restaurantapptwo.main.AsyncResult
import com.davidgrath.restaurantapptwo.main.EmptyAdapter
import com.davidgrath.restaurantapptwo.meals.adapters.TrendingMealRecyclerAdapter
import com.davidgrath.restaurantapptwo.meals.viewmodels.TrendingViewModel
import com.davidgrath.restaurantapptwo.meals.viewmodels.factories.TrendingViewModelFactory

class TrendingActivity : AppCompatActivity() {

    lateinit var binding: ActivityTrendingBinding
    lateinit var viewModel : TrendingViewModel
    lateinit var adapter: TrendingMealRecyclerAdapter
    lateinit var emptyAdapter: EmptyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrendingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarTrending)
        //TODO Sometimes I use this pattern, other times I call the custom factory's create()
        // directly. Make it consistent across all Activities
        viewModel =
            ViewModelProvider(this,
                TrendingViewModelFactory((application as com.davidgrath.restaurantapptwo.application.RestaurantAppTwo).getMealUseCase()))
                .get(TrendingViewModel::class.java)

        val columnCount = resources.getInteger(R.integer.column_count)
        val layoutManager = GridLayoutManager(this, columnCount)

        adapter = TrendingMealRecyclerAdapter(emptyList(), object : TrendingMealRecyclerAdapter.ItemClickListener {
            override fun onItemClicked() {
                val orderIntent = Intent(this@TrendingActivity, MealOrderActivity::class.java)
                startActivity(orderIntent)
            }
        })
        emptyAdapter = EmptyAdapter(errorText = "There was a problem. Tap to retry (TODO Implement)")
        val recyclerView = binding.recyclerViewTrending
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        viewModel.getTrendingMeals().observe(this) { result ->
            when(result) {
                is AsyncResult.Failure -> {
                    binding.progressBarTrending.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                    recyclerView.adapter = emptyAdapter
                }
                is AsyncResult.Processing -> {
                    binding.progressBarTrending.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                is AsyncResult.Success -> {
                    binding.progressBarTrending.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE

                    if(result.data.isEmpty()) {
                        recyclerView.adapter = emptyAdapter
                    } else {
                        adapter.trendingMeals = result.data
                        recyclerView.adapter = adapter
                    }
                }
            }
        }
    }
}
package com.davidgrath.restaurantapptwo.restaurants

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.davidgrath.restaurantapptwo.R
import com.davidgrath.restaurantapptwo.databinding.ActivityMostPopularBinding
import com.davidgrath.restaurantapptwo.main.AsyncResult
import com.davidgrath.restaurantapptwo.main.EmptyAdapter
import com.davidgrath.restaurantapptwo.restaurants.adapters.RestaurantRecyclerAdapter
import com.davidgrath.restaurantapptwo.restaurants.viewmodels.MostPopularViewModel
import com.davidgrath.restaurantapptwo.restaurants.viewmodels.factories.MostPopularViewModelFactory

class MostPopularActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMostPopularBinding
    private lateinit var adapter : RestaurantRecyclerAdapter
    private lateinit var emptyAdapter : EmptyAdapter
    private lateinit var viewModel : MostPopularViewModel
    private var listSizeCached = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMostPopularBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarMostPopular)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        window.statusBarColor = resources.getColor(R.color.white)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true
        //TODO Dagger
        viewModel = ViewModelProvider(this,
            MostPopularViewModelFactory((application as com.davidgrath.restaurantapptwo.application.RestaurantAppTwo).getRestaurantUseCase()))
            .get(MostPopularViewModel::class.java)
        adapter = RestaurantRecyclerAdapter(emptyList())
        viewModel.getMostPopularRestaurants().observe(this) { result ->
            when(result) {
                is AsyncResult.Failure -> {
                    binding.progressBarMostPopular.visibility = View.GONE
                    binding.recyclerViewMostPopular.visibility = View.VISIBLE
                    binding.recyclerViewMostPopular.adapter = emptyAdapter
                    listSizeCached = 0
                    invalidateOptionsMenu()
                }
                is AsyncResult.Processing -> {
                    binding.progressBarMostPopular.visibility = View.VISIBLE
                    binding.recyclerViewMostPopular.visibility = View.GONE
                    listSizeCached = 0
                    invalidateOptionsMenu()
                }
                is AsyncResult.Success -> {
                    binding.progressBarMostPopular.visibility = View.GONE
                    binding.recyclerViewMostPopular.visibility = View.VISIBLE
                    if(result.data.isEmpty()) {
                        binding.recyclerViewMostPopular.adapter = emptyAdapter
                    } else {
                        adapter.restaurants = result.data
                        binding.recyclerViewMostPopular.adapter = adapter
                    }
                    listSizeCached = result.data.size
                    invalidateOptionsMenu()
                }
            }
        }
        emptyAdapter = EmptyAdapter(errorText = "There was a problem. Tap to try again (TODO implement)")
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerViewMostPopular.adapter = adapter
        binding.recyclerViewMostPopular.layoutManager = layoutManager
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_most_popular, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val itemCountItem = menu!!.findItem(R.id.menuitem_most_popular_item_count)
        //TODO Modify for LiveData
        val itemCount = listSizeCached
        //TODO Plurals, I think there's a framework for that
        itemCountItem.title = "$itemCount places"
        return true
    }
}
package com.example.restaurantapptwo.restaurants

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurantapptwo.R
import com.example.restaurantapptwo.application.RestaurantAppTwo
import com.example.restaurantapptwo.databinding.ActivityMostPopularBinding
import com.example.restaurantapptwo.restaurants.adapters.RestaurantRecyclerAdapter
import com.example.restaurantapptwo.restaurants.viewmodels.MostPopularViewModel
import com.example.restaurantapptwo.restaurants.viewmodels.factories.MostPopularViewModelFactory

class MostPopularActivity : AppCompatActivity() {

    lateinit var binding : ActivityMostPopularBinding
    lateinit var adapter : RestaurantRecyclerAdapter
    lateinit var viewModel : MostPopularViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMostPopularBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //TODO Dagger
        viewModel = ViewModelProvider(this,
            MostPopularViewModelFactory((application as RestaurantAppTwo).getRestaurantUseCase()))
            .get(MostPopularViewModel::class.java)
        adapter = RestaurantRecyclerAdapter(viewModel.getMostPopularRestaurants())
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
        val itemCount = viewModel.getMostPopularRestaurants().size
        //TODO Plurals, I think there's a framework for that
        itemCountItem.title = "$itemCount places"
        return true
    }
}
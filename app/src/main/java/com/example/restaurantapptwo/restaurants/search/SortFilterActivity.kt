package com.example.restaurantapptwo.restaurants.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.lifecycle.ViewModelProvider
import com.example.restaurantapptwo.R
import com.example.restaurantapptwo.databinding.ActivitySortFilterBinding

class SortFilterActivity : AppCompatActivity() {

    lateinit var viewModel: SortFilterViewModel
    lateinit var binding : ActivitySortFilterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySortFilterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(SortFilterViewModel::class.java)
        setSupportActionBar(binding.toolbarSortFilter)
        title = "Filter"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_sort_filter, menu)
        return true
    }
}
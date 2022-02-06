package com.example.restaurantapptwo.meals

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.restaurantapptwo.meals.adapters.TrendingMealRecyclerAdapter
import com.example.restaurantapptwo.databinding.ActivityTrendingBinding

class TrendingActivity : AppCompatActivity() {

    lateinit var binding: ActivityTrendingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrendingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = GridLayoutManager(this, 2)
//        val adapter = TrendingMealRecyclerAdapter(object : TrendingMealRecyclerAdapter.ItemClickListener {
//            override fun onItemClicked() {
//                val orderIntent = Intent(this@TrendingActivity, MealOrderActivity::class.java)
//                startActivity(orderIntent)
//            }
//        })
//        val recyclerView = binding.recyclerViewTrending
//        recyclerView.layoutManager = layoutManager
//        recyclerView.adapter = adapter
    }
}
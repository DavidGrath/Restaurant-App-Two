package com.example.restaurantapptwo.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurantapptwo.R
import com.example.restaurantapptwo.application.RestaurantAppTwo
import com.example.restaurantapptwo.databinding.FragmentHomeListsBinding
import com.example.restaurantapptwo.meals.MealDetailsActivity
import com.example.restaurantapptwo.meals.TrendingActivity
import com.example.restaurantapptwo.meals.adapters.TrendingMealRecyclerAdapter
import com.example.restaurantapptwo.restaurants.MostPopularActivity
import com.example.restaurantapptwo.restaurants.adapters.LatestFeedsRecyclerAdapter

class HomeListsFragment : Fragment(), View.OnClickListener {

    lateinit var binding : FragmentHomeListsBinding
    lateinit var viewModel : HomeViewModel
    lateinit var trendingMealAdapter : TrendingMealRecyclerAdapter
    lateinit var latestFeedsAdapter : LatestFeedsRecyclerAdapter

    val images = arrayOf(
        R.drawable.sample_food_square_1,
        R.drawable.sample_food_square_2,
        R.drawable.sample_food_square_3,
        R.drawable.sample_food_1,
        R.drawable.sample_food_2,
        R.drawable.sample_food_3,
        R.drawable.sample_food_4,
        R.drawable.sample_food_5,
        R.drawable.sample_food_6,
        R.drawable.sample_food_7,
        R.drawable.sample_food_8,
        R.drawable.sample_food_9
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeListsBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(parentFragment as HomeFragment).get(HomeViewModel::class.java)
        val trendingMealLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        trendingMealAdapter = TrendingMealRecyclerAdapter(viewModel.getTrendingMeals(), object : TrendingMealRecyclerAdapter.ItemClickListener {
            override fun onItemClicked() {
                val mealDetailsIntent = Intent(requireContext(), MealDetailsActivity::class.java)
                startActivity(mealDetailsIntent)
            }
        })
        binding.recyclerViewHomeTrending.adapter = trendingMealAdapter
        binding.recyclerViewHomeTrending.layoutManager = trendingMealLayoutManager

        val latestFeedLayoutManager = LinearLayoutManager(requireContext())
        latestFeedsAdapter = LatestFeedsRecyclerAdapter(viewModel.getLatestFeed())
        binding.recyclerViewHomeFeed.layoutManager = latestFeedLayoutManager
        binding.recyclerViewHomeFeed.adapter = latestFeedsAdapter

        // TODO Is there a better way than include?
        val mostPopularRestaurant = viewModel.getMostPopularRestaurant()
        with(binding.includeHomeMostPopular) {
            imageviewPopularRestaurantPicture1.setImageResource(images.random())
            imageviewPopularRestaurantPicture2.setImageResource(images.random())
            imageviewPopularRestaurantPicture3.setImageResource(images.random())
            textviewPopularRestaurantTitle.text = mostPopularRestaurant.name
            textviewPopularRestaurantAddress.text = mostPopularRestaurant.address
            textviewPopularRestaurantOpenTime.text = mostPopularRestaurant.openTimeText
            ratingBarPopularRestaurant.rating = mostPopularRestaurant.averageRating
        }
        binding.textviewHomeTrendingViewAll.setOnClickListener(this)
        binding.textviewHomePopularViewAll.setOnClickListener(this)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onClick(v: View?) {
        v?.let {
            when(it) {
                binding.textviewHomeTrendingViewAll -> {
                    val trendingIntent = Intent(requireContext(), TrendingActivity::class.java)
                    startActivity(trendingIntent)
                }
                binding.textviewHomePopularViewAll -> {
                    val popularIntent = Intent(requireContext(), MostPopularActivity::class.java)
                    startActivity(popularIntent)
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(): HomeListsFragment {
            return HomeListsFragment().apply {

            }
        }
    }
}
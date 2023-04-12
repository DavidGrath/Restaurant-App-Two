package com.davidgrath.restaurantapptwo.main

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.davidgrath.restaurantapptwo.Constants
import com.davidgrath.restaurantapptwo.R
import com.davidgrath.restaurantapptwo.databinding.FragmentHomeListsBinding
import com.davidgrath.restaurantapptwo.meals.MealDetailsActivity
import com.davidgrath.restaurantapptwo.meals.TrendingActivity
import com.davidgrath.restaurantapptwo.meals.adapters.TrendingMealRecyclerAdapter
import com.davidgrath.restaurantapptwo.restaurants.MostPopularActivity
import com.davidgrath.restaurantapptwo.restaurants.RestaurantDetailsActivity
import com.davidgrath.restaurantapptwo.restaurants.adapters.LatestFeedsRecyclerAdapter
import com.davidgrath.restaurantapptwo.restaurants.entities.RestaurantSummaryUI

class HomeListsFragment : Fragment(), View.OnClickListener, LatestFeedsRecyclerAdapter.RestaurantClickListener {

    lateinit var binding : FragmentHomeListsBinding
    lateinit var viewModel : HomeViewModel
    lateinit var trendingMealAdapter : TrendingMealRecyclerAdapter
    lateinit var emptyTrendingAdapter: EmptyAdapter
    lateinit var latestFeedsAdapter : LatestFeedsRecyclerAdapter
    lateinit var emptyLatestFeedsAdapter: EmptyAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeListsBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(parentFragment as HomeFragment).get(HomeViewModel::class.java)
        val trendingMealLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        trendingMealAdapter = TrendingMealRecyclerAdapter(emptyList(), object : TrendingMealRecyclerAdapter.ItemClickListener {
            override fun onItemClicked() {
                val mealDetailsIntent = Intent(requireContext(), MealDetailsActivity::class.java)
                startActivity(mealDetailsIntent)
            }
        })
        emptyTrendingAdapter = EmptyAdapter(errorText = "No results")
        viewModel.getTrendingMeals().observe(requireActivity()) { result ->
            when(result) {
                is AsyncResult.Failure -> {
                    binding.progressBarHomeTrending.visibility = View.GONE
                    binding.recyclerViewHomeTrending.visibility = View.VISIBLE
                    binding.recyclerViewHomeTrending.adapter = emptyTrendingAdapter
                }
                is AsyncResult.Processing -> {
                    binding.progressBarHomeTrending.visibility = View.VISIBLE
                    binding.recyclerViewHomeTrending.visibility = View.GONE
                }
                is AsyncResult.Success -> {
                    binding.progressBarHomeTrending.visibility = View.GONE
                    binding.recyclerViewHomeTrending.visibility = View.VISIBLE

                    if(result.data.isEmpty()) {
                        binding.recyclerViewHomeTrending.adapter = emptyTrendingAdapter
                    } else {
                        trendingMealAdapter.trendingMeals = result.data
                        binding.recyclerViewHomeTrending.adapter = trendingMealAdapter
                    }
                }
            }
        }
        binding.recyclerViewHomeTrending.adapter = trendingMealAdapter
        binding.recyclerViewHomeTrending.layoutManager = trendingMealLayoutManager

        val latestFeedLayoutManager = LinearLayoutManager(requireContext())
        latestFeedsAdapter = LatestFeedsRecyclerAdapter(emptyList(), this)
        emptyLatestFeedsAdapter = EmptyAdapter(errorText = "No results")
        binding.recyclerViewHomeFeed.layoutManager = latestFeedLayoutManager
        viewModel.getLatestFeed().observe(requireActivity()) { result ->
            when(result) {
                is AsyncResult.Failure -> {
                    binding.progressBarHomeLatestFeed.visibility = View.GONE
                    binding.recyclerViewHomeFeed.visibility = View.VISIBLE
                    binding.recyclerViewHomeFeed.adapter = emptyLatestFeedsAdapter
                }
                is AsyncResult.Processing -> {
                    binding.progressBarHomeLatestFeed.visibility = View.VISIBLE
                    binding.recyclerViewHomeFeed.visibility = View.GONE
                }
                is AsyncResult.Success -> {
                    binding.progressBarHomeLatestFeed.visibility = View.GONE
                    binding.recyclerViewHomeFeed.visibility = View.VISIBLE
                    if(result.data.isEmpty()) {
                        binding.recyclerViewHomeFeed.adapter = emptyLatestFeedsAdapter
                    } else {
                        latestFeedsAdapter.restaurantFeed = result.data
                        binding.recyclerViewHomeFeed.adapter = latestFeedsAdapter
                    }
                }
            }
        }
        binding.recyclerViewHomeFeed.adapter = latestFeedsAdapter

        // TODO Is there a better way than include?
        viewModel.getMostPopularRestaurant().observe(viewLifecycleOwner) { result ->
            when(result) {
                is AsyncResult.Failure -> {
                    binding.progressBarHomeMostPopular.visibility = View.GONE
                    // using .root property. Interesting
                    binding.includeHomeMostPopular.root.visibility = View.GONE
                    binding.textviewHomePopularEmpty.visibility = View.VISIBLE
                    binding.textviewHomePopularEmpty.text = "No results"
                }
                is AsyncResult.Processing -> {
                    binding.progressBarHomeMostPopular.visibility = View.VISIBLE
                    binding.includeHomeMostPopular.root.visibility = View.GONE
                    binding.textviewHomePopularEmpty.visibility = View.GONE
                }
                is AsyncResult.Success -> {
                    binding.progressBarHomeMostPopular.visibility = View.GONE
                    binding.includeHomeMostPopular.root.visibility = View.VISIBLE
                    binding.textviewHomePopularEmpty.visibility = View.GONE
                    val mostPopularRestaurant = result.data
                    with(binding.includeHomeMostPopular) {
                        Glide.with(requireContext())
                            .asBitmap()
                            .placeholder(R.drawable.simple_image_placeholder)
                            .fallback(R.drawable.simple_image_placeholder)
                            .centerCrop()
                            .load(Constants.FOOD_IMAGE_IDS.random())
                            .into(object : BitmapImageViewTarget(imageviewPopularRestaurantPicture1) {
                                override fun setResource(resource: Bitmap?) {
                                    resource?.let {
                                        val roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(resources, it)
                                        roundedBitmapDrawable.cornerRadius = resources.getDimension(R.dimen.space_size_1)
                                        view.setImageDrawable(roundedBitmapDrawable)
                                    }
                                }
                            })
                        Glide.with(requireContext())
                            .asBitmap()
                            .placeholder(R.drawable.simple_image_placeholder)
                            .fallback(R.drawable.simple_image_placeholder)
                            .centerCrop()
                            .load(Constants.FOOD_IMAGE_IDS.random())
                            .into(object : BitmapImageViewTarget(imageviewPopularRestaurantPicture2) {
                                override fun setResource(resource: Bitmap?) {
                                    resource?.let {
                                        val roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(resources, it)
                                        roundedBitmapDrawable.cornerRadius = resources.getDimension(R.dimen.space_size_1)
                                        view.setImageDrawable(roundedBitmapDrawable)
                                    }
                                }
                            })
                        Glide.with(requireContext())
                            .asBitmap()
                            .placeholder(R.drawable.simple_image_placeholder)
                            .fallback(R.drawable.simple_image_placeholder)
                            .centerCrop()
                            .load(Constants.FOOD_IMAGE_IDS.random())
                            .into(object : BitmapImageViewTarget(imageviewPopularRestaurantPicture3) {
                                override fun setResource(resource: Bitmap?) {
                                    resource?.let {
                                        val roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(resources, it)
                                        roundedBitmapDrawable.cornerRadius = resources.getDimension(R.dimen.space_size_1)
                                        view.setImageDrawable(roundedBitmapDrawable)
                                    }
                                }
                            })
                        textviewPopularRestaurantTitle.text = mostPopularRestaurant.name
                        textviewPopularRestaurantAddress.text = mostPopularRestaurant.address
                        textviewPopularRestaurantOpenTime.text = mostPopularRestaurant.openTimeText
                        ratingBarPopularRestaurant.rating = mostPopularRestaurant.averageRating
                    }
                }
            }
        }
        binding.textviewHomeTrendingViewAll.setOnClickListener(this)
        binding.textviewHomePopularViewAll.setOnClickListener(this)
        return binding.root
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

    override fun onRestaurantClicked(restaurant: RestaurantSummaryUI) {
        val restaurantIntent = Intent(requireContext(), RestaurantDetailsActivity::class.java)
        restaurantIntent.putExtra(Constants.Extras.RESTAURANT_DETAILS_RESTAURANT_ID, restaurant.id)
        startActivity(restaurantIntent)
    }

    companion object {
        @JvmStatic
        fun newInstance(): HomeListsFragment {
            return HomeListsFragment().apply {

            }
        }
    }
}
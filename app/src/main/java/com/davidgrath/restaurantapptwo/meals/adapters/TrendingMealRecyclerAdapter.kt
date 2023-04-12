package com.davidgrath.restaurantapptwo.meals.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.davidgrath.restaurantapptwo.R
import com.davidgrath.restaurantapptwo.databinding.RecyclerviewMealTrendingBinding
import com.davidgrath.restaurantapptwo.meals.entities.MealSummaryUI

class TrendingMealRecyclerAdapter(trendingMeals : List<MealSummaryUI>, var itemClickListener: ItemClickListener? = null) : RecyclerView.Adapter<TrendingMealRecyclerAdapter.TrendingMealViewHolder>() {

    var trendingMeals : List<MealSummaryUI> = trendingMeals
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    interface ItemClickListener {
        fun onItemClicked()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingMealViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerviewMealTrendingBinding.inflate(inflater, parent, false)
        return TrendingMealViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrendingMealViewHolder, position: Int) {
        val context = holder.itemView.context
        val meal = trendingMeals[position]
        with(holder.binding) {
            textviewMealTrendingRestaurantName.text = meal.restaurantName
            textviewMealTrendingMealName.text = meal.name
            textviewMealTrendingPrice.text = meal.price
            Glide.with(holder.itemView.context)
                .load(com.davidgrath.restaurantapptwo.Constants.FOOD_IMAGE_IDS.random())
                .fallback(R.drawable.simple_image_placeholder)
                .into(imageviewMealTrendingPicture)
            ratingBarMealTrending.rating = meal.restaurantRating
            root.setOnClickListener {
                itemClickListener?.onItemClicked()
            }
        }
    }

    override fun getItemCount(): Int {
        return trendingMeals.size
    }

    class TrendingMealViewHolder(val binding : RecyclerviewMealTrendingBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}
package com.example.restaurantapptwo.meals.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantapptwo.R
import com.example.restaurantapptwo.databinding.RecyclerviewMealTrendingBinding
import com.example.restaurantapptwo.meals.entities.MealSummaryUI
import kotlin.random.Random

class TrendingMealRecyclerAdapter(private val trendingMeals : List<MealSummaryUI>, var itemClickListener: ItemClickListener? = null) : RecyclerView.Adapter<TrendingMealRecyclerAdapter.TrendingMealViewHolder>() {

    interface ItemClickListener {
        fun onItemClicked()
    }

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
            imageviewMealTrendingPicture.setImageResource(images.random())
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
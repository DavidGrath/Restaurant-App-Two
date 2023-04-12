package com.davidgrath.restaurantapptwo.restaurants.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.davidgrath.restaurantapptwo.Constants
import com.davidgrath.restaurantapptwo.R
import com.davidgrath.restaurantapptwo.databinding.RecyclerviewRestaurantDetailsReviewBinding
import com.davidgrath.restaurantapptwo.restaurants.entities.ReviewUI

class RestaurantReviewRecyclerAdapter(val reviews : List<ReviewUI>) : RecyclerView.Adapter<RestaurantReviewRecyclerAdapter.RestaurantReviewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantReviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerviewRestaurantDetailsReviewBinding.inflate(inflater, parent, false)
        return RestaurantReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantReviewViewHolder, position: Int) {
        val review = reviews[position]
        with(holder.binding) {
            textviewRestaurantDetailsReviewItemUsername.text = review.username
            textviewRestaurantDetailsReviewItemReview.text = review.reviewText
            textviewRestaurantDetailsReviewItemRelativeTime.text = review.relativeTime
            ratingBarRestaurantDetailsReviewItem.rating = review.rating.toFloat()
            Glide.with(holder.itemView.context)
                .load(Constants.PEOPLE_IMAGE_IDS.random())
                .circleCrop()
                .placeholder(R.drawable.simple_image_placeholder)
                .fallback(R.drawable.simple_image_placeholder)
                .into(imageviewRestaurantDetailsReviewItemProfilePicture)

        }
    }

    override fun getItemCount(): Int {
        return reviews.size
    }

    class RestaurantReviewViewHolder(val binding : RecyclerviewRestaurantDetailsReviewBinding) : RecyclerView.ViewHolder(binding.root)
}
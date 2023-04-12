package com.davidgrath.restaurantapptwo.restaurants.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.davidgrath.restaurantapptwo.databinding.RecyclerviewRestaurantDetailsMenuBinding

class RestaurantMenuRecyclerAdapter(val menuItems : List<Pair<String, Int>>) : RecyclerView.Adapter<RestaurantMenuRecyclerAdapter.RestaurantMenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantMenuViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerviewRestaurantDetailsMenuBinding.inflate(inflater, parent, false)
        return RestaurantMenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantMenuViewHolder, position: Int) {
        val menuItem = menuItems[position]
        with(holder.binding) {
            textviewRestaurantDetailsMenuItemTitle.text = menuItem.first
            textviewRestaurantDetailsMenuItemQuantity.text = "${menuItem.second}"
        }
    }

    override fun getItemCount(): Int {
        return menuItems.size
    }

    class RestaurantMenuViewHolder(val binding : RecyclerviewRestaurantDetailsMenuBinding) : RecyclerView.ViewHolder(binding.root)
}
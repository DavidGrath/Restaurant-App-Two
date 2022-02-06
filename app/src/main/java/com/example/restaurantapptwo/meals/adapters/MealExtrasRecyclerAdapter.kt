package com.example.restaurantapptwo.meals.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantapptwo.databinding.ListviewMealDetailsExtrasBinding

//TODO Change to ListView. RecyclerView seems like overkill
class MealExtrasRecyclerAdapter(val extras : List<Pair<String, String>>) : RecyclerView.Adapter<MealExtrasRecyclerAdapter.ExtrasViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExtrasViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListviewMealDetailsExtrasBinding.inflate(inflater, parent, false)
        return ExtrasViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExtrasViewHolder, position: Int) {
        val binding = holder.binding
        binding.textviewMealDetailsExtrasName.text = extras[position].first
        binding.textviewMealDetailsExtrasPrice.text = extras[position].second
    }

    override fun getItemCount(): Int {
        return extras.size
    }

    class ExtrasViewHolder(val binding : ListviewMealDetailsExtrasBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}
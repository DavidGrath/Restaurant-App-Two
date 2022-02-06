package com.example.restaurantapptwo.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantapptwo.databinding.RecyclerviewEmptyBinding

class EmptyAdapter(private val matchParent : Boolean = true, private val errorText : String = "") : RecyclerView.Adapter<EmptyAdapter.EmptyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmptyViewHolder {
        val binding = RecyclerviewEmptyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmptyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmptyViewHolder, position: Int) {
        val height = if(matchParent) LinearLayout.LayoutParams.MATCH_PARENT else LinearLayout.LayoutParams.WRAP_CONTENT
        val linearLayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height)
        val binding = holder.binding
        //TODO this might cause a crash. Can't remember why
        binding.linearLayoutEmpty.layoutParams = linearLayoutParams
        binding.textviewEmptyError.text = errorText
    }

    override fun getItemCount(): Int {
        return 1
    }

    class EmptyViewHolder(val binding : RecyclerviewEmptyBinding) : RecyclerView.ViewHolder(binding.root){

    }
}
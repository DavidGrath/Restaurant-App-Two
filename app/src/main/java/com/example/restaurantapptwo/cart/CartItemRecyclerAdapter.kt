package com.example.restaurantapptwo.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantapptwo.R
import com.example.restaurantapptwo.databinding.RecyclerviewCartItemBinding

class CartItemRecyclerAdapter(private var cartItems : List<CartItemUI>) : RecyclerView.Adapter<CartItemRecyclerAdapter.CartItemViewHolder>() {
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
    fun setCartItems(cartItems: List<CartItemUI>) {
        this.cartItems = cartItems
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        val binding = RecyclerviewCartItemBinding.inflate(LayoutInflater.from(parent.context))
        val viewHolder = CartItemViewHolder(binding)
        return viewHolder
    }

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        val cartItem = cartItems[position]
        with(holder.binding) {
            cartItemTitle.text = cartItem.name
            cartItemDescription.text = cartItem.description
            cartItemPrice.text = "\u20A6${cartItem.price}"
            imageviewCartItemPicture.setImageResource(images.random())
        }
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }

    class CartItemViewHolder(val binding : RecyclerviewCartItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}
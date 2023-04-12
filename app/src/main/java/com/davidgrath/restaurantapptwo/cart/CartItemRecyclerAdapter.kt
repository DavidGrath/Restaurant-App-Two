package com.davidgrath.restaurantapptwo.cart

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.davidgrath.restaurantapptwo.R
import com.davidgrath.restaurantapptwo.cart.entities.ui.CartItemUI
import com.davidgrath.restaurantapptwo.databinding.RecyclerviewCartItemBinding

class CartItemRecyclerAdapter(private var cartItems : List<CartItemUI>) : RecyclerView.Adapter<CartItemRecyclerAdapter.CartItemViewHolder>() {

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
            val context = holder.itemView.context
            cartItemTitle.text = cartItem.name
            cartItemDescription.text = cartItem.description
            cartItemPrice.text = "\u20A6${cartItem.price}"
            Glide.with(context)
                .asBitmap()
                .load(com.davidgrath.restaurantapptwo.Constants.FOOD_IMAGE_IDS.random())
                .centerCrop()
                .fallback(R.drawable.simple_image_placeholder)
                .into(object : BitmapImageViewTarget(imageviewCartItemPicture) {
                    override fun setResource(resource: Bitmap?) {
                        if(resource != null) {
                            val roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(
                                context.resources, resource
                            )
                            roundedBitmapDrawable.cornerRadius =
                                context.resources.getDimension(R.dimen.space_size_1)
                            view.setImageDrawable(roundedBitmapDrawable)
                        }
                    }

                })

        }
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }

    class CartItemViewHolder(val binding : RecyclerviewCartItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}
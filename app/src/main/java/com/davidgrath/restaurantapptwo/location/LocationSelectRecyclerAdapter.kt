package com.davidgrath.restaurantapptwo.location

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.davidgrath.restaurantapptwo.R
import com.davidgrath.restaurantapptwo.databinding.RecyclerviewLocationSelectItemBinding
import com.davidgrath.restaurantapptwo.location.entities.data.SimpleLocation

class LocationSelectRecyclerAdapter(locations: List<SimpleLocation>, var listener : ItemClickListener? = null) : RecyclerView.Adapter<LocationSelectRecyclerAdapter.LocationSelectViewHolder>() {

    var locations : List<SimpleLocation> = locations
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    interface ItemClickListener {
        fun onLocationClicked(position: Int, location : SimpleLocation)
    }

    var selectedItemPosition : Int = -1
        set(value) {
            if(field != -1) {
                notifyItemChanged(field)
            }
            field = value
            notifyItemChanged(value)
        }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationSelectViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerviewLocationSelectItemBinding.inflate(inflater, parent, false)
        return LocationSelectViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LocationSelectViewHolder, position: Int) {
        with(holder.binding) {
            val context = holder.itemView.context
            textviewLocationSelectName.text = locations[position].name
            if(position == selectedItemPosition) {
                val doneDrawable = context.getDrawable(R.drawable.ic_baseline_done_24)
                doneDrawable!!.setTint(context.resources.getColor(R.color.pale_green))
                textviewLocationSelectName.setCompoundDrawablesWithIntrinsicBounds(null, null, doneDrawable, null)
                //TODO Theme color reference
                textviewLocationSelectName.setTextColor(context.resources.getColor(R.color.pale_green))
            } else {
                textviewLocationSelectName.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
                textviewLocationSelectName.setTextColor(context.resources.getColor(R.color.black))
            }
            root.setOnClickListener { v ->
                listener?.onLocationClicked(position, locations[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return locations.size
    }

    class LocationSelectViewHolder(val binding : RecyclerviewLocationSelectItemBinding) : RecyclerView.ViewHolder(binding.root)
}
package com.example.restaurantapptwo.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantapptwo.R
import com.example.restaurantapptwo.databinding.FragmentLocationListBinding
import com.example.restaurantapptwo.databinding.RecyclerviewLocationSelectItemBinding

class LocationListFragment : Fragment() {

    lateinit var binding: FragmentLocationListBinding
    lateinit var adapter: LocationSelectAdapter
    val simpleList = listOf(
        "Agege",
        "Ajeromi Ifelodun",
        "Alimosho",
        "Amuwo-Odofin",
        "Apapa",
        "Badagry",
        "Epe",
        "Ifako-Ijaye",
        "Ikeja",
        "Kosofe",
        "Ikorodu",
        "Ojo"
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLocationListBinding.inflate(inflater, container, false)

        adapter = LocationSelectAdapter(simpleList, object : LocationSelectAdapter.ItemClickListener {
            override fun onLocationClicked(position: Int) {
                adapter.selectedItemPosition = position
            }
        })
        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerviewLocationList.adapter = adapter
        binding.recyclerviewLocationList.layoutManager = layoutManager
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(): LocationListFragment {
            return LocationListFragment().apply {
                arguments = Bundle().apply {
                }
            }
        }
    }

    class LocationSelectAdapter(val locations: List<String>, var listener : ItemClickListener? = null) : RecyclerView.Adapter<LocationSelectAdapter.LocationSelectViewHolder>() {

        interface ItemClickListener {
            fun onLocationClicked(position: Int)
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
                textviewLocationSelectName.text = locations[position]
                if(position == selectedItemPosition) {
                    val doneDrawable = context.getDrawable(R.drawable.ic_baseline_done_24)
                    textviewLocationSelectName.setCompoundDrawables(null, null, doneDrawable, null)
                    textviewLocationSelectName.setTextColor(context.resources.getColor(R.color.pale_green))
                } else {
                    textviewLocationSelectName.setCompoundDrawables(null, null, null, null)
                    textviewLocationSelectName.setTextColor(context.resources.getColor(R.color.black))
                }
                root.setOnClickListener { v ->
                    listener?.onLocationClicked(position)
                }
            }
        }

        override fun getItemCount(): Int {
            return locations.size
        }

        class LocationSelectViewHolder(val binding : RecyclerviewLocationSelectItemBinding) : RecyclerView.ViewHolder(binding.root)
    }
}
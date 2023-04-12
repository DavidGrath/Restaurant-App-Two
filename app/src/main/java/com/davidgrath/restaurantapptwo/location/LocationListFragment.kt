package com.davidgrath.restaurantapptwo.location

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.davidgrath.restaurantapptwo.R
import com.davidgrath.restaurantapptwo.databinding.FragmentLocationListBinding
import com.davidgrath.restaurantapptwo.location.entities.data.SimpleLocation
import com.davidgrath.restaurantapptwo.main.EmptyAdapter

class LocationListFragment : Fragment(), View.OnClickListener {

    lateinit var binding: FragmentLocationListBinding
    lateinit var adapter: LocationSelectRecyclerAdapter
    lateinit var emptyAdapter: EmptyAdapter
    lateinit var viewModel : LocationSelectViewModel

    private var listener : LocationListFragmentListener? = null

    interface LocationListFragmentListener {
        fun onLocationItemSelected(location : SimpleLocation)
        fun onCurrentLocationSelectedList()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is LocationListFragmentListener) {
            listener = context
        }

    }

    //TODO Add back button to toolbar. Low priority
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLocationListBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(LocationSelectViewModel::class.java)
        adapter = LocationSelectRecyclerAdapter(emptyList(), object : LocationSelectRecyclerAdapter.ItemClickListener {
            override fun onLocationClicked(position: Int, location: SimpleLocation) {
                if(adapter.selectedItemPosition != position) {
                    adapter.selectedItemPosition = position
                } else {
                    listener?.onLocationItemSelected(location)
                }
            }
        })
        emptyAdapter = EmptyAdapter(errorText = getString(R.string.location_list_fragment_empty_error_text))
        viewModel.locationListLiveData.observe(requireActivity()) { newList ->
            if(newList.isEmpty()) {
                binding.recyclerviewLocationList.adapter = emptyAdapter
            } else {
                binding.recyclerviewLocationList.adapter = adapter
                adapter.locations = newList
            }
        }
        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerviewLocationList.adapter = adapter
        binding.recyclerviewLocationList.layoutManager = layoutManager
        binding.edittextSearchLocation.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                viewModel.searchLocations(s!!.toString())
            }
        })
        binding.textInputLayoutSearchLocation.setEndIconOnClickListener { v ->
            adapter.selectedItemPosition = -1
            binding.edittextSearchLocation.text!!.clear()
            viewModel.clearSearch()
        }
        binding.textviewLocationListCurrentLocation.setOnClickListener(this)
        return binding.root
    }

    override fun onClick(v: View?) {
        v?.let {
            when(it) {
                binding.textviewLocationListCurrentLocation -> {
                    listener?.onCurrentLocationSelectedList()
                }
                else -> {

                }
            }
        }
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
}
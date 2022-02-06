package com.example.restaurantapptwo.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurantapptwo.R
import com.example.restaurantapptwo.databinding.FragmentHomeSearchBinding
import com.example.restaurantapptwo.restaurants.adapters.RestaurantRecyclerAdapter

class HomeSearchFragment : Fragment() {

    lateinit var binding : FragmentHomeSearchBinding
    lateinit var viewModel : HomeViewModel
    lateinit var adapter : RestaurantRecyclerAdapter
    lateinit var emptyAdapter: EmptyAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeSearchBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(parentFragment as HomeFragment).get(HomeViewModel::class.java)
        adapter = RestaurantRecyclerAdapter(emptyList())
        emptyAdapter = EmptyAdapter(errorText = getString(R.string.home_search_fragment_empty_error_prompt))
        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewHomeSearch.adapter = adapter
        binding.recyclerViewHomeSearch.layoutManager = layoutManager
        viewModel.searchResults.observe(viewLifecycleOwner) { results ->
            if(results.isEmpty()) {
                //TODO this might cause a brief moment of error visibility when closing search
                binding.recyclerViewHomeSearch.adapter = emptyAdapter
            } else {
                binding.recyclerViewHomeSearch.adapter = adapter
                adapter.setRestaurants(results)
            }
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(): HomeSearchFragment {
            return HomeSearchFragment().apply {

            }
        }
    }
}
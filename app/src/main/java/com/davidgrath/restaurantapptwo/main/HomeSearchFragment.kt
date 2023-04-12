package com.davidgrath.restaurantapptwo.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.davidgrath.restaurantapptwo.R
import com.davidgrath.restaurantapptwo.databinding.FragmentHomeSearchBinding
import com.davidgrath.restaurantapptwo.restaurants.adapters.RestaurantRecyclerAdapter

class HomeSearchFragment : Fragment() {

    lateinit var binding : FragmentHomeSearchBinding
    lateinit var viewModel : HomeViewModel
    lateinit var adapter : RestaurantRecyclerAdapter
    lateinit var emptyAdapter: EmptyAdapter
    lateinit var errorAdapter: EmptyAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeSearchBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(parentFragment as HomeFragment).get(HomeViewModel::class.java)
        adapter = RestaurantRecyclerAdapter(emptyList())
        emptyAdapter = EmptyAdapter(errorText = getString(R.string.home_search_fragment_empty_error_prompt))
        errorAdapter = EmptyAdapter(errorText = "Something went wrong. Tap to retry (TODO Implement)")
        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewHomeSearch.adapter = adapter
        binding.recyclerViewHomeSearch.layoutManager = layoutManager
        viewModel.searchResults.observe(viewLifecycleOwner) { result ->
            when(result) {
                is AsyncResult.Failure -> {
                    binding.progressBarHomeSearch.visibility = View.GONE
                    binding.recyclerViewHomeSearch.visibility = View.VISIBLE
                    binding.recyclerViewHomeSearch.adapter = errorAdapter
                }
                is AsyncResult.Processing -> {
                    binding.progressBarHomeSearch.visibility = View.VISIBLE
                    binding.recyclerViewHomeSearch.visibility = View.GONE
                }
                is AsyncResult.Success -> {
                    binding.progressBarHomeSearch.visibility = View.GONE
                    binding.recyclerViewHomeSearch.visibility = View.VISIBLE
                    if(result.data.isEmpty()) {
                        //TODO this might cause a brief moment of error visibility when closing search
                        binding.recyclerViewHomeSearch.adapter = emptyAdapter
                    } else {
                        binding.recyclerViewHomeSearch.adapter = adapter
                        adapter.restaurants = result.data
                    }
                }
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
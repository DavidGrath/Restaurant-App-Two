package com.example.restaurantapptwo.main

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurantapptwo.R
import com.example.restaurantapptwo.application.RestaurantAppTwo
import com.example.restaurantapptwo.databinding.FragmentHomeBinding
import com.example.restaurantapptwo.restaurants.adapters.LatestFeedsRecyclerAdapter
import com.example.restaurantapptwo.meals.MealDetailsActivity
import com.example.restaurantapptwo.restaurants.MostPopularActivity
import com.example.restaurantapptwo.meals.TrendingActivity
import com.example.restaurantapptwo.meals.adapters.TrendingMealRecyclerAdapter
import com.example.restaurantapptwo.restaurants.search.SortFilterActivity


class HomeFragment : Fragment() {

    lateinit var binding : FragmentHomeBinding
    lateinit var viewModel : HomeViewModel
    lateinit var searchFragment: HomeSearchFragment
    lateinit var listsFragment: HomeListsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val factory = HomeViewModelFactory((requireActivity().application as RestaurantAppTwo).getHomeUseCase())
        viewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)
        if(savedInstanceState == null) {
            listsFragment = HomeListsFragment.newInstance()
            searchFragment = HomeSearchFragment.newInstance()
        }
        childFragmentManager.beginTransaction()
            .add(R.id.frame_layout_home, listsFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .addToBackStack(null)
            .commit()
        binding.searchViewHome.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.search(it)
                }
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                query?.let {
                    viewModel.search(it)
                }
                return true
            }
        })
        binding.searchViewHome.setOnCloseListener {
            viewModel.clearSearch()
            childFragmentManager.beginTransaction()
                .replace(R.id.frame_layout_home, listsFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
            true
        }
        binding.searchViewHome.setOnSearchClickListener {
            childFragmentManager.beginTransaction()
                .replace(R.id.frame_layout_home, searchFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menuitem_home_filter -> {
                val filterIntent = Intent(requireContext(), SortFilterActivity::class.java)
                startActivity(filterIntent)
            }
        }
        return true
    }

    companion object {
        @JvmStatic
        fun newInstance(): HomeFragment {
            return HomeFragment().apply {

            }
        }
    }
}
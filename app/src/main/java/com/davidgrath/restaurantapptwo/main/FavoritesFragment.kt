package com.davidgrath.restaurantapptwo.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.davidgrath.restaurantapptwo.databinding.FragmentFavoritesBinding

class FavoritesFragment : Fragment() {

    lateinit var binding : FragmentFavoritesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        binding.toolbarFavorites.title = "Favorites"
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(): FavoritesFragment {
            return FavoritesFragment().apply {
            }
        }
    }
}
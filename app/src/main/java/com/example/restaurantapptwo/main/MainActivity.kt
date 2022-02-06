package com.example.restaurantapptwo.main

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.restaurantapptwo.auth.ProfileFragment
import com.example.restaurantapptwo.R
import com.example.restaurantapptwo.cart.CartActivity
import com.example.restaurantapptwo.databinding.ActivityMainBinding
import com.example.restaurantapptwo.restaurants.DiscoverFragment
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    lateinit var homeFragment: HomeFragment
    lateinit var discoverFragment : DiscoverFragment
    lateinit var favoritesFragment: FavoritesFragment
    lateinit var profileFragment: ProfileFragment

    val bottomNavListener = object : NavigationBarView.OnItemSelectedListener {
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when(item.itemId)  {
                R.id.menuitem_main_bottom_home -> {
                    setCurrentFragment(homeFragment)
                }
                R.id.menuitem_main_bottom_discover -> {
                    setCurrentFragment(discoverFragment)
                }
                R.id.menuitem_main_bottom_cart -> {
                    val cartIntent = Intent(this@MainActivity, CartActivity::class.java)
                    startActivity(cartIntent)
                }
                R.id.menuitem_main_bottom_favorites -> {
                    setCurrentFragment(favoritesFragment)
                }
                R.id.menuitem_main_bottom_profile -> {
                    setCurrentFragment(profileFragment)
                }
            }
            return false
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if(savedInstanceState == null) {
            homeFragment = HomeFragment.newInstance()
            discoverFragment = DiscoverFragment.newInstance()
            favoritesFragment = FavoritesFragment.newInstance()
            profileFragment = ProfileFragment.newInstance()
        }
        binding.bottomNavMain.setOnItemSelectedListener(bottomNavListener)
        supportFragmentManager.beginTransaction()
            .add(R.id.frame_layout_main, homeFragment)
            .commit()
    }

    private fun setCurrentFragment(fragment : Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout_main, fragment)
            .commit()
    }
}



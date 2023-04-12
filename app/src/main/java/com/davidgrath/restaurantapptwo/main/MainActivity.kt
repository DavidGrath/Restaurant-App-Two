package com.davidgrath.restaurantapptwo.main

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.davidgrath.restaurantapptwo.R
import com.davidgrath.restaurantapptwo.auth.ProfileFragment
import com.davidgrath.restaurantapptwo.cart.CartActivity
import com.davidgrath.restaurantapptwo.databinding.ActivityMainBinding
import com.davidgrath.restaurantapptwo.restaurants.DiscoverFragment
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    lateinit var homeFragment: HomeFragment
    lateinit var discoverFragment : DiscoverFragment
    lateinit var favoritesFragment: FavoritesFragment
    lateinit var profileFragment: ProfileFragment
    lateinit var currFragment : String

    val bottomNavListener = object : NavigationBarView.OnItemSelectedListener {
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when(item.itemId)  {
                R.id.menuitem_main_bottom_home -> {
                    setCurrentFragment("home")
                }
                R.id.menuitem_main_bottom_discover -> {
                    setCurrentFragment("discover")
                }
                R.id.menuitem_main_bottom_cart -> {
                    val cartIntent = Intent(this@MainActivity, CartActivity::class.java)
                    startActivity(cartIntent)
                    return false
                }
                R.id.menuitem_main_bottom_favorites -> {
                    setCurrentFragment("favorites")
                }
                R.id.menuitem_main_bottom_profile -> {
                    setCurrentFragment("profile")
                }
            }
            return true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.statusBarColor = Color.TRANSPARENT
        if(savedInstanceState == null) {
            homeFragment = HomeFragment.newInstance()
            discoverFragment = DiscoverFragment.newInstance()
            favoritesFragment = FavoritesFragment.newInstance()
            profileFragment = ProfileFragment.newInstance()
            supportFragmentManager.beginTransaction()
                .add(R.id.frame_layout_main, homeFragment, "home")
                .add(R.id.frame_layout_main, discoverFragment, "discover")
                .add(R.id.frame_layout_main, favoritesFragment, "favorites")
                .add(R.id.frame_layout_main, profileFragment, "profile")
                .hide(discoverFragment)
                .hide(favoritesFragment)
                .hide(profileFragment)
                .setReorderingAllowed(true)
                .commit()
            currFragment = "home"
        } else {
            val manager = supportFragmentManager
            homeFragment = manager.findFragmentByTag("home") as HomeFragment
            discoverFragment = manager.findFragmentByTag("discover") as DiscoverFragment
            favoritesFragment = manager.findFragmentByTag("favorites") as FavoritesFragment
            profileFragment = manager.findFragmentByTag("profile") as ProfileFragment
            currFragment = savedInstanceState.getString("currFragment", "home")
        }
        binding.bottomNavMain.setOnItemSelectedListener(bottomNavListener)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("currFragment", currFragment)
    }

    private fun fragmentTitleToFragment(title : String) : Fragment {
        return when(title) {
            "home" -> homeFragment
            "discover" -> discoverFragment
            "favorites" -> favoritesFragment
            "profile" -> profileFragment
            else -> homeFragment
        }
    }
    private fun setCurrentFragment(title : String) {
        supportFragmentManager.beginTransaction()
            .hide(fragmentTitleToFragment(currFragment))
            .show(fragmentTitleToFragment(title))
            .commit()
        currFragment = title
    }
}



package com.example.restaurantapptwo.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.example.restaurantapptwo.main.MainActivity
import com.example.restaurantapptwo.R
import com.example.restaurantapptwo.databinding.ActivityLocationSelectBinding

class LocationSelectActivity : AppCompatActivity(), LocationPromptFragment.LocationPromptFragmentListener {

    lateinit var binding : ActivityLocationSelectBinding
    lateinit var promptFragment: LocationPromptFragment
    lateinit var listFragment : LocationListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationSelectBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if(savedInstanceState == null) {
            promptFragment = LocationPromptFragment.newInstance()
            listFragment = LocationListFragment.newInstance()
        }
        supportFragmentManager.beginTransaction()
            .add(R.id.frame_layout_location_select, promptFragment)
            .commit()
    }

    override fun onSelectLocationManually() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout_location_select, listFragment, null)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .addToBackStack(null)
            .commit()
    }

    override fun onUseCurrentLocation() {
        val mainIntent = Intent(this, MainActivity::class.java)
        startActivity(mainIntent)
        finish()
    }
}
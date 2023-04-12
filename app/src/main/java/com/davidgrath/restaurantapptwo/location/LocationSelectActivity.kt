package com.davidgrath.restaurantapptwo.location

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.davidgrath.restaurantapptwo.R
import com.davidgrath.restaurantapptwo.databinding.ActivityLocationSelectBinding
import com.davidgrath.restaurantapptwo.location.entities.data.SimpleLocation
import com.davidgrath.restaurantapptwo.main.MainActivity

class LocationSelectActivity : AppCompatActivity(),
    LocationPromptFragment.LocationPromptFragmentListener,
    LocationListFragment.LocationListFragmentListener {

    private lateinit var binding : ActivityLocationSelectBinding
    private lateinit var viewModel : LocationSelectViewModel
    private lateinit var promptFragment: LocationPromptFragment
    private lateinit var listFragment : LocationListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationSelectBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //TODO Figure out extension functions and use that one
        window.statusBarColor = resources.getColor(R.color.light_grey)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true
        viewModel = ViewModelProvider(this,
            LocationSelectViewModelFactory((application as com.davidgrath.restaurantapptwo.application.RestaurantAppTwo).locationHelper,
                ((application as com.davidgrath.restaurantapptwo.application.RestaurantAppTwo).getAuthUseCase()))
        ).get(LocationSelectViewModel::class.java)
        if(savedInstanceState == null) {
            promptFragment = LocationPromptFragment.newInstance()
            listFragment = LocationListFragment.newInstance()
            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.frame_layout_location_select, promptFragment, "profile")
                .add(R.id.frame_layout_location_select, listFragment, "list")
                .hide(listFragment)
                .commit()
        } else {
            promptFragment = supportFragmentManager.findFragmentByTag("profile") as LocationPromptFragment
//            if(supportFragmentManager.findFragmentByTag("list") != null) {
                listFragment = supportFragmentManager.findFragmentByTag("list") as LocationListFragment
//            }
        }

    }

    override fun onSelectLocationManually() {
        supportFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .hide(promptFragment)
            .show(listFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .addToBackStack(null)
            .commit()
    }

    override fun onCurrentLocationSelected() {
        viewModel.setLocation(viewModel.getDefaultLocation().toString())
        val mainIntent = Intent(this, MainActivity::class.java)
        startActivity(mainIntent)
        finish()
    }

    override fun onLocationItemSelected(location: SimpleLocation) {
        viewModel.setLocation(location.toString())
        val mainIntent = Intent(this, MainActivity::class.java)
        startActivity(mainIntent)
        finish()
    }

    override fun onCurrentLocationSelectedList() {
        viewModel.setLocation(viewModel.getDefaultLocation().toString())
        val mainIntent = Intent(this, MainActivity::class.java)
        startActivity(mainIntent)
        finish()
    }
}
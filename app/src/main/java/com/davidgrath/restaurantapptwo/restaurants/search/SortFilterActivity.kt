package com.davidgrath.restaurantapptwo.restaurants.search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.ViewModelProvider
import com.davidgrath.restaurantapptwo.R
import com.davidgrath.restaurantapptwo.databinding.ActivitySortFilterBinding

class SortFilterActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var viewModel: SortFilterViewModel
    lateinit var binding : ActivitySortFilterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySortFilterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(SortFilterViewModel::class.java)
        setSupportActionBar(binding.toolbarSortFilter)
        window.statusBarColor = resources.getColor(R.color.white)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true
        binding.buttonSortFilterApply.setOnClickListener(this)
        val sortBy = intent.getStringExtra("sortBy")
        val openNow = intent.getStringExtra("openNow")
        val creditCards = intent.getStringExtra("creditCards")
        val alcoholServed = intent.getStringExtra("alcoholServed")
        val minPrice = intent.getStringExtra("minPrice")
        val maxPrice = intent.getStringExtra("maxPrice")
        //TODO Do stuff with values
        when(sortBy) {
            "topRated" -> {
                binding.radiobuttonSortGroupTopRated.isChecked = true
            }
            "nearestMe" -> {
                binding.radiobuttonSortGroupNearestMe.isChecked = true
            }
            "costAscending" -> {
                binding.radiobuttonSortGroupCostAsc.isChecked = true
            }
            "costDescending" -> {
                binding.radiobuttonSortGroupCostDesc.isChecked = true
            }
            "mostPopular" -> {
                binding.radiobuttonSortGroupMostPopular.isChecked = true
            } else -> {
            }
        }
        binding.checkboxFilterOpenNow.isChecked = openNow.toBoolean()
        binding.checkboxFilterCreditCards.isChecked = creditCards.toBoolean()
        binding.checkboxFilterAlcoholServed.isChecked = alcoholServed.toBoolean()
        //TODO adjust for either null instead of both
        if(minPrice != null && maxPrice != null) {
            binding.rangeSliderFilterPriceRange.setValues(minPrice.toFloat(), maxPrice.toFloat())
        } else {
            binding.rangeSliderFilterPriceRange.setValues(100F, 10_000F)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_sort_filter, menu)
        return true
    }

    override fun onClick(v: View?) {
        v?.let {
            when(it) {
                binding.buttonSortFilterApply -> {
                    val result = Intent()

                    val sortByOption = binding.radiogroupSort.checkedRadioButtonId
                    val sortBy : String? = if(sortByOption == -1) {
                        null
                    } else {
                        when(sortByOption) {
                            R.id.radiobutton_sort_group_top_rated -> {
                                "topRated"
                            }
                            R.id.radiobutton_sort_group_nearest_me -> {
                                "nearestMe"
                            }
                            R.id.radiobutton_sort_group_cost_asc -> {
                                "costAscending"
                            }
                            R.id.radiobutton_sort_group_cost_desc -> {
                                "costDescending"
                            }
                            R.id.radiobutton_sort_group_most_popular -> {
                                "mostPopular"
                            } else -> {
                                null
                            }
                        }
                    }
                    result.putExtra("sortBy", sortBy)

                    val openNow = binding.checkboxFilterOpenNow.isChecked.toString()
                    result.putExtra("openNow", openNow)

                    val creditCards = binding.checkboxFilterCreditCards.isChecked.toString()
                    result.putExtra("creditCards", creditCards)

                    val alcoholServed = binding.checkboxFilterAlcoholServed.isChecked.toString()
                    result.putExtra("alcoholServed", alcoholServed)
                    Log.d("Sort", binding.rangeSliderFilterPriceRange.values.toString())
//                    val minPrice = binding.rangeSliderFilterPriceRange.values[0].toString()
//                    result.putExtra("minPrice", minPrice)
//
//                    val maxPrice = binding.rangeSliderFilterPriceRange.values[1].toString()
//                    result.putExtra("maxPrice", maxPrice)
                    setResult(RESULT_OK, result)
                    finish()
                }
            }
        }
    }
}
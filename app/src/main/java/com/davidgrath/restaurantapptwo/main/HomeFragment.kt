package com.davidgrath.restaurantapptwo.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.davidgrath.restaurantapptwo.R
import com.davidgrath.restaurantapptwo.databinding.FragmentHomeBinding
import com.davidgrath.restaurantapptwo.restaurants.search.SortFilterActivity


class HomeFragment : Fragment() {

    lateinit var binding : FragmentHomeBinding
    lateinit var viewModel : HomeViewModel
    lateinit var searchFragment: HomeSearchFragment
    lateinit var listsFragment: HomeListsFragment

    val menuItemClickListener = object : Toolbar.OnMenuItemClickListener {
        override fun onMenuItemClick(item: MenuItem?): Boolean {
            when(item!!.itemId) {
                R.id.menuitem_home_filter -> {
                    launcher.launch(null)
                }
            }
            return true
        }
    }
    val contract = object : ActivityResultContract<Map<String, String>, Map<String, String?>?>() {
        override fun createIntent(context: Context, input: Map<String, String>?): Intent {
            val filterIntent = Intent(requireContext(), SortFilterActivity::class.java)
            viewModel.getSortFilterOptions()?.forEach { entry ->
                filterIntent.putExtra(entry.key, entry.value)
            }
            return filterIntent
        }

        override fun parseResult(resultCode: Int, intent: Intent?): Map<String, String?>? {
            if(resultCode != Activity.RESULT_OK) {
                return null
            }
            val options = arrayOf("sortBy", "openNow", "creditCards", "alcoholServed", "minPrice", "maxPrice")
            val map = options.map {
                it to intent!!.getStringExtra(it)
            }.toMap()
            return map
        }
    }
    val callback = ActivityResultCallback<Map<String, String?>?> { result ->
        viewModel.setSearchSortFilterOptions(result)
    }
    lateinit var launcher: ActivityResultLauncher<Map<String, String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launcher = registerForActivityResult(contract, callback)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.toolbarHomeFragment.title = "Browse"
        binding.toolbarHomeFragment.inflateMenu(R.menu.menu_home)
        binding.toolbarHomeFragment.setOnMenuItemClickListener(menuItemClickListener)
        val app = (requireActivity().application as com.davidgrath.restaurantapptwo.application.RestaurantAppTwo)
        val factory = HomeViewModelFactory(app.getMealUseCase(), app.getRestaurantUseCase())
        viewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)
        if(savedInstanceState == null) {
            listsFragment = HomeListsFragment.newInstance()
            searchFragment = HomeSearchFragment.newInstance()
            childFragmentManager.beginTransaction()
                .add(R.id.frame_layout_home, listsFragment)
                .add(R.id.frame_layout_home, searchFragment)
                .hide(searchFragment)
                .setReorderingAllowed(true)
                .commit()
        }
        binding.textInputLayoutSearchHome.setStartIconOnClickListener { v ->
            if(!binding.editTextSearchHome.text.isNullOrEmpty()) {
                //TODO Implement search fragment
            }
        }
        binding.textInputLayoutSearchHome.setEndIconOnClickListener { v ->

        }
        binding.editTextSearchHome.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                viewModel.search(s!!.toString())
            }
        })
        binding.editTextSearchHome.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    viewModel.search(binding.editTextSearchHome.text.toString())
                    return true
                }
                event?.let {
//                    if (it.action == KeyEvent.ACTION_DOWN && actionId == EditorInfo.IME_ACTION_DONE) {
//                        viewModel.search(binding.editTextSearchHome.text.toString())
//                        return true
//                    }
                }
                return false
            }
        })

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(): HomeFragment {
            return HomeFragment().apply {

            }
        }
    }
}
package com.davidgrath.restaurantapptwo.auth

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.davidgrath.restaurantapptwo.Constants
import com.davidgrath.restaurantapptwo.R
import com.davidgrath.restaurantapptwo.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.toolbarProfile.title = "Profile"
        binding.toolbarProfile.inflateMenu(R.menu.menu_profile)
        binding.toolbarProfile.setOnMenuItemClickListener(object : Toolbar.OnMenuItemClickListener {
            override fun onMenuItemClick(item: MenuItem?): Boolean {
                when (item?.itemId) {
                    R.id.menuitem_profile_edit -> {
                        //TODO Edit
                    }
                }
                return true
            }
        })
        Glide.with(requireContext())
            .asBitmap()
            .placeholder(R.drawable.simple_image_placeholder)
            .fallback(R.drawable.ic_baseline_person_24)
            .circleCrop()
            .load(Constants.PEOPLE_IMAGE_IDS.random())
            .into(binding.imageviewProfilePicture)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(): ProfileFragment {
            return ProfileFragment().apply {
            }
        }
    }

}
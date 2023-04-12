package com.davidgrath.restaurantapptwo

import android.graphics.Color
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

class Extensions {
    //TODO Learn how to use extension functions
    fun AppCompatActivity.makeStatusBarTransparent() {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.statusBarColor = Color.TRANSPARENT
    }
}
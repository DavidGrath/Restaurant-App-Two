package com.davidgrath.restaurantapptwo.main

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    private val dialog = NoInternetDialogFragment.newInstance()
    private lateinit var connectivityManager: ConnectivityManager

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {

        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if(networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                    networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)) {
                    closeDialog()
                } else {
                    showDialog()
                }

            } else {
                if(networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
                    closeDialog()
                } else {
                    showDialog()
                }
            }
        }

        override fun onUnavailable() {
            showDialog()
        }

        override fun onLost(network: Network) {
            showDialog()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val builder = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            builder.addCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
        }
        val networkRequest = builder.build()
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)

        if(connectivityManager.activeNetworkInfo == null) {
            showDialog()
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Log.d("ActiveNetwork", connectivityManager.activeNetwork?.toString()?:"null")
        } else {
            Log.d("ActiveNetworkInfo", connectivityManager.activeNetworkInfo?.toString()?:"null")
        }
//        if(connectivityManager.activeNetwork == null) {
//            showDialog()
//        }
    }
    
    fun showDialog() {
        if(!dialog.isShowing) {
            dialog.show(supportFragmentManager, null)
            dialog.isShowing = true
        }
    }
    
    fun closeDialog() {
        if(dialog.isShowing) {
            dialog.dismiss()
        }
    }

    //TODO Replace with LiveData from Application class so as not to rely on Lifecycle
    override fun onStop() {
        super.onStop()
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}
package com.khanhtruong.cryptocompose.util

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager

object Utils {
    // --------------------------------
    // Internet Utils
    // --------------------------------
    /**
     * Check if the device is connected with the Internet.
     */
    @SuppressLint("MissingPermission")
    fun isConnectedToInternet(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo

        return activeNetwork?.isConnected == true
    }
}
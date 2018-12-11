package com.example.fact.global

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by Lalit Khandelwal on 11, December, 2018.
 * lalitkhandelwal99@gmail.com
 */
object NetworkUtils {

    /**
     * For check internet connection active or not
     * @param context Reference of view
     * @return true if connected or connecting else not connected
     */
    @Suppress("DEPRECATION")
    fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
}

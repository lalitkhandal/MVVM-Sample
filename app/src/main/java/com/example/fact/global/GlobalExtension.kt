package com.example.fact.global

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

/**
 * Created by Lalit Khandelwal on 11, December, 2018.
 * lalitkhandelwal99@gmail.com
 */


/**
 * For check internet connection active or not
 * @return true if connected or connecting else not connected
 */

fun Fragment.isNetworkConnected(isConnected: (Boolean) -> Unit) {
    val cm = activity?.getSystemService(Context.CONNECTIVITY_SERVICE)
    var activeNetwork: NetworkInfo? = null
    if (cm != null) {
        cm as ConnectivityManager
        activeNetwork = cm.activeNetworkInfo
    }
    @Suppress("DEPRECATION")
    return isConnected(activeNetwork != null && activeNetwork.isConnectedOrConnecting)
}


fun View?.showSnackBar(message: String?) {
    this?.let {
        message?.let {
            val snackBar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
            snackBar.show()
        }
    }
}

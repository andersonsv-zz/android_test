package br.com.andersonsv.test.extension

import android.content.Context
import android.net.ConnectivityManager


fun Context.isNetworkConnected(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val netInfo = cm.activeNetworkInfo
    return netInfo != null && netInfo.isConnected
}


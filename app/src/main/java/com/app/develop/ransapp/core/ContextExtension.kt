package com.app.develop.ransapp.core

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.M)
fun Context.hasInternet() = !this.isAirplaneModeActive() && this.isConnected()

fun Context.isAirplaneModeActive(): Boolean {
    return Settings.Global.getInt(contentResolver, Settings.Global.AIRPLANE_MODE_ON, 0) != 0
}

@RequiresApi(Build.VERSION_CODES.M)
fun Context.isConnected(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return cm.getNetworkCapabilities(cm.activeNetwork)?.hasCapability((NetworkCapabilities.NET_CAPABILITY_INTERNET)) ?: false
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.screenSize(sizeType: CoreSizeType) : Int {
    val displayMetrics = resources.displayMetrics
    return if (sizeType == CoreSizeType.HEIGHT) displayMetrics.heightPixels else displayMetrics.widthPixels
}

enum class CoreSizeType {
    HEIGHT,
    WIDTH;
}
package com.example.melichallenge.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.melichallenge.MainActivity.Companion.mainContext
import java.text.NumberFormat

class Utilities {
    fun formatPrice(price: String) : String {
        val format = NumberFormat.getCurrencyInstance()
        format.setMaximumFractionDigits(0)
        var sFormat = format.format(price.toDouble()).toString()
        sFormat = sFormat.replace(",",".")
        return sFormat
    }

    fun isConnected(): Boolean {
        val connectivityManager =
            mainContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) return true
            else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) return true
            else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) return true
        }
        return false
    }

    fun transformPictureUrl(urlPicture: String): String{
        var newPictureUrl = urlPicture.replace("http:","https:")
        newPictureUrl = newPictureUrl.replace("I.jpg","Z.jpg")
        return newPictureUrl
    }
}
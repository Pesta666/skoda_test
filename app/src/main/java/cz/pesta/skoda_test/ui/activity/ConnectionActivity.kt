package cz.pesta.skoda_test.ui.activity

import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import androidx.activity.ComponentActivity
import cz.pesta.skoda_test.helpers.connectedToNetwork

abstract class ConnectionActivity : ComponentActivity() {
    private val networkCallback = object : ConnectivityManager.NetworkCallback() {

        override fun onAvailable(network: Network) {
            connectedToNetwork = isNetworkAvailable()
            super.onAvailable(network)
        }

        override fun onLosing(network: Network, maxMsToLive: Int) {
            connectedToNetwork = isNetworkAvailable()
            super.onLosing(network, maxMsToLive)
        }

        override fun onLost(network: Network) {
            connectedToNetwork = false
            super.onLost(network)
        }

        override fun onUnavailable() {
            connectedToNetwork = isNetworkAvailable()
            super.onUnavailable()
        }

        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {
            connectedToNetwork = isNetworkAvailable()
            super.onCapabilitiesChanged(network, networkCapabilities)
        }

        override fun onLinkPropertiesChanged(network: Network, linkProperties: LinkProperties) {
            connectedToNetwork = isNetworkAvailable()
            super.onLinkPropertiesChanged(network, linkProperties)
        }

        override fun onBlockedStatusChanged(network: Network, blocked: Boolean) {
            connectedToNetwork = isNetworkAvailable()
            super.onBlockedStatusChanged(network, blocked)
        }

        private fun isNetworkAvailable(): Boolean {
            val connectivityManager =
                getSystemService(CONNECTIVITY_SERVICE) as? ConnectivityManager
            val nw = connectivityManager?.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                //for other device how are able to connect with Ethernet
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                //for check internet over Bluetooth
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                else -> false
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerNetworkCallback()
    }

    override fun onDestroy() {
        unregisterNetworkCallback()
        super.onDestroy()
    }

    private fun registerNetworkCallback() {
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
        val connectivityManager =
            getSystemService(ConnectivityManager::class.java) as ConnectivityManager
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }

    @Suppress("SwallowedException")
    private fun unregisterNetworkCallback() {
        try {
            val connectivityManager =
                getSystemService(ConnectivityManager::class.java) as ConnectivityManager
            connectivityManager.unregisterNetworkCallback(networkCallback)
        } catch (e: Exception) {
            // no action
        }
    }
}
package ru.vlabum.android.gb.androidretrofit.ui

import android.content.Context
import android.net.ConnectivityManager
import ru.vlabum.android.gb.androidretrofit.App
import ru.vlabum.android.gb.androidretrofit.mvp.model.api.INetworkStatus

class NetworkStatus : INetworkStatus {

    override fun getStatus(): INetworkStatus.Status {
        val cm = App.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        if (null != activeNetwork) {
            when (activeNetwork.type) {
                ConnectivityManager.TYPE_WIFI -> return INetworkStatus.Status.WIFI

                ConnectivityManager.TYPE_ETHERNET -> return INetworkStatus.Status.ETHERNET

                ConnectivityManager.TYPE_MOBILE -> return INetworkStatus.Status.MOBILE
            }
            return INetworkStatus.Status.OTHER
        }
        return INetworkStatus.Status.OFFLINE
    }

    override fun isOnline(): Boolean {
        return !getStatus().equals(INetworkStatus.Status.OFFLINE)
    }

    override fun isWifi(): Boolean {
        return getStatus().equals(INetworkStatus.Status.WIFI)
    }

    override fun isEthernet(): Boolean {
        return getStatus().equals(INetworkStatus.Status.ETHERNET)
    }

    override fun isMobile(): Boolean {
        return getStatus().equals(INetworkStatus.Status.MOBILE)
    }

    override fun isOffline(): Boolean {
        return getStatus().equals(INetworkStatus.Status.OFFLINE)
    }
}
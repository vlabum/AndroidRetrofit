package ru.vlabum.android.gb.androidretrofit.mvp.model.api

interface INetworkStatus {
    enum class Status {
        WIFI,
        MOBILE,
        ETHERNET,
        OTHER,
        OFFLINE
    }

    fun getStatus(): Status

    fun isOnline(): Boolean

    fun isWifi(): Boolean

    fun isEthernet(): Boolean

    fun isMobile(): Boolean

    fun isOffline(): Boolean

}
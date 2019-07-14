package ru.vlabum.android.gb.androidretrofit.mvp.model.image

import ru.vlabum.android.gb.androidretrofit.mvp.model.api.INetworkStatus
import ru.vlabum.android.gb.androidretrofit.mvp.model.cache.ImageCache

interface IImageLoader<T> {

    var networkStatus: INetworkStatus

    var imageCache: ImageCache

    fun loadInto(url: String, container: T)
}
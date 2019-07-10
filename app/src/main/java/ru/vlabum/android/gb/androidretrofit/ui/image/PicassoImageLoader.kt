package ru.vlabum.android.gb.androidretrofit.ui.image

import android.widget.ImageView
import com.squareup.picasso.Picasso
import ru.vlabum.android.gb.androidretrofit.mvp.model.api.INetworkStatus
import ru.vlabum.android.gb.androidretrofit.mvp.model.cache.ImageCache
import ru.vlabum.android.gb.androidretrofit.mvp.model.image.IImageLoader
import javax.inject.Inject

class PicassoImageLoader : IImageLoader<ImageView> {

    @Inject
    override lateinit var networkStatus: INetworkStatus

    @Inject
    override lateinit var imageCache: ImageCache

    override fun loadInto(url: String, container: ImageView) {
        Picasso.get().load(url).into(container)
    }
}
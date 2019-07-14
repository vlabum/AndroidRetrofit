package ru.vlabum.android.gb.androidretrofit.ui.image

import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import ru.vlabum.android.gb.androidretrofit.mvp.model.api.INetworkStatus
import ru.vlabum.android.gb.androidretrofit.mvp.model.cache.ImageCache
import ru.vlabum.android.gb.androidretrofit.mvp.model.image.IImageLoader
import javax.inject.Inject

class GlideImageLoader : IImageLoader<ImageView> {

    @Inject
    override lateinit var networkStatus: INetworkStatus

    @Inject
    override lateinit var imageCache: ImageCache

    override fun loadInto(url: String, container: ImageView) {
        if (networkStatus.isOnline()) {
            Glide.with(container.context)
                .asBitmap()
                .load(url)
                .listener(object : RequestListener<Bitmap> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Bitmap?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        resource?.let { imageCache.saveImage(url, it) }
                        return false
                    }
                })
                .into(container)
        } else {
            Glide.with(container.context)
                .load(imageCache.getFile(url))
                .into(container)
        }
    }
}
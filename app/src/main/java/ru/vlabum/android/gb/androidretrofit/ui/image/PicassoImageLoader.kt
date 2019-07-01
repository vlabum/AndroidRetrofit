package ru.vlabum.android.gb.androidretrofit.ui.image

import android.widget.ImageView
import com.squareup.picasso.Picasso
import ru.vlabum.android.gb.androidretrofit.mvp.model.image.IImageLoader

class PicassoImageLoader : IImageLoader<ImageView> {
    override fun loadInto(url: String, container: ImageView) {
        Picasso.get().load(url).into(container)
    }
}
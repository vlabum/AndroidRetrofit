package ru.vlabum.android.gb.androidretrofit.mvp.model.cache

import io.paperdb.Paper
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.IImage
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.Image

class PaperImageCache : IImageCacheDb {

    companion object {
        private val imagesBook = "images"
    }

    override fun findFirst(url: String): IImage? {
        val path = Paper.book(imagesBook).path
        val image = Paper.book(imagesBook).read<Image>(url)
//        val image = Image()
//        image.url = url
//        image.path = path
        return image
    }

    override fun contains(url: String): Boolean {
        val value = findFirst(url)
        return value != null
    }

    override fun clear() {
        val keys: List<String> = Paper.book(imagesBook).allKeys
        for (key in keys) {
            Paper.book(imagesBook).delete(key)
        }
    }

    override fun saveImage(url: String, path: String) {
        val image = Image()
        image.url = url
        image.path = path
        Paper.book(imagesBook).write<Image>(url, image)
    }

}
package ru.vlabum.android.gb.androidretrofit.mvp.model.cache

import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.IImage

interface IImageCacheDb {

    fun findFirst(url: String): IImage?
    fun contains(url: String): Boolean
    fun clear()
    fun saveImage(url: String, path: String)

}
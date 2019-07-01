package ru.vlabum.android.gb.androidretrofit.mvp.model.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}
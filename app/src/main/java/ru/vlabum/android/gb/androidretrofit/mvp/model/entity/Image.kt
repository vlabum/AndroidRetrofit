package ru.vlabum.android.gb.androidretrofit.mvp.model.entity

class Image : IImage {

    internal lateinit var url: String
    internal lateinit var path: String

    override fun setUrl(url: String) {
        this.url = url
    }

    override fun setPath(path: String) {
        this.path = path
    }

    override fun getPath(): String {
        return path
    }

}
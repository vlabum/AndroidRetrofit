package ru.vlabum.android.gb.androidretrofit.mvp.model.entity.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.IImage


open class RealmImage() : RealmObject(), IImage {

    @PrimaryKey
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
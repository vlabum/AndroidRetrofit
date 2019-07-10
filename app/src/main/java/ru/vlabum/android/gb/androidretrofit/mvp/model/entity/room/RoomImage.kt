package ru.vlabum.android.gb.androidretrofit.mvp.model.entity.room

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.IImage

@Entity
class RoomImage() : IImage {

    @NonNull
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
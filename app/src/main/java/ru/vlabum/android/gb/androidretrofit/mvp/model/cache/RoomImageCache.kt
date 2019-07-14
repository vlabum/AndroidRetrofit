package ru.vlabum.android.gb.androidretrofit.mvp.model.cache

import io.reactivex.Observable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.IImage
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.room.RoomImage
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.room.db.Database

class RoomImageCache : IImageCacheDb {

    override fun findFirst(url: String): IImage? {
        var image: RoomImage? = null
        val observable: Observable<RoomImage> = Observable.fromCallable {
            Database.getInstance().getImageDao().findByUrl(url)
        }
        val d = observable.subscribeOn(Schedulers.io())
            .subscribe(Consumer { img ->
                image = img
            })
        return image
//        return Database.getInstance().getImageDao()
//            .findByUrl(url)
    }

    override fun contains(url: String): Boolean {
        return if (Database.getInstance().getImageDao().contains(url) == 1) true else false
    }

    override fun clear() {
        Database.getInstance().getImageDao().clear()
    }

    override fun saveImage(url: String, path: String) {

        val d = Observable.fromCallable {
            var roomImage: RoomImage? = Database.getInstance().getImageDao()
                .findByUrl(url)
            if (roomImage == null) {
                roomImage = RoomImage()
                roomImage.setUrl(url)
                roomImage.setPath(path)
            }

            Database.getInstance().getImageDao().insert(roomImage)
        }
            .subscribeOn(Schedulers.io())
            .subscribe()
    }
}
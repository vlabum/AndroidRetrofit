package ru.vlabum.android.gb.androidretrofit.mvp.model.cache

import io.realm.Realm
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.IImage
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.realm.RealmImage

class RealmImageCache : IImageCacheDb {

    override fun findFirst(url: String): IImage? {
        val image: IImage? = Realm.getDefaultInstance().where(RealmImage::class.java).equalTo("url", url).findFirst()
        return image
    }

    override fun saveImage(url: String, path: String) {
        Realm.getDefaultInstance().executeTransactionAsync { realm ->
            val realmImage = realm.where(RealmImage::class.java).equalTo("url", url).findFirst()
            if (realmImage == null) {
                val cachedImage = RealmImage()
                cachedImage.setUrl(url)
                cachedImage.setPath(path)
                realm.copyToRealm(cachedImage)
            }
        }
    }

    override fun clear() {
        Realm.getDefaultInstance().executeTransaction { realm -> realm.delete(RealmImage::class.java) }
    }

    override fun contains(url: String): Boolean {
        return Realm.getDefaultInstance().where(RealmImage::class.java).equalTo("url", url).count() > 0
    }
}
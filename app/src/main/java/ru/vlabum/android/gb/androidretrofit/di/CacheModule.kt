package ru.vlabum.android.gb.androidretrofit.di

import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.vlabum.android.gb.androidretrofit.mvp.model.cache.*
import javax.inject.Named
import javax.inject.Singleton

@Module
open class CacheModule {

    @Singleton
    @Provides
    open fun imageCache(@Named("imageRoom") imageCacheDb: IImageCacheDb): ImageCache {
        return ImageCache(imageCacheDb, AndroidSchedulers.mainThread())
    }

    @Provides
    open fun getCache(@Named("room") cache: ICache): ICache {
        return cache
    }

    @Named("room")
    @Provides
    open fun roomCach(): ICache {
        return RoomCache()
    }

    @Named("realm")
    @Singleton
    @Provides
    open fun realmCach(): ICache {
        return RealmCache()
    }

//    @Named("paper")
//    @Singleton
//    @Provides
//    open fun paperCach(): ICache {
//        return PaperCache()
//    }

    @Named("imageRoom")
    @Singleton
    @Provides
    fun roomImageCache(): IImageCacheDb {
        return RoomImageCache()
    }

    @Named("imageRealm")
    @Singleton
    @Provides
    fun realmImageCache(): IImageCacheDb {
        return RealmImageCache()
    }

//    @Named("imagePaper")
//    @Singleton
//    @Provides
//    fun paperImageCache(): IImageCacheDb {
//        return PaperImageCache()
//    }
}
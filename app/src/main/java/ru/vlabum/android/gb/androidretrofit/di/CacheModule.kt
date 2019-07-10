package ru.vlabum.android.gb.androidretrofit.di

import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.vlabum.android.gb.androidretrofit.mvp.model.cache.*
import javax.inject.Named
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun imageCache(@Named("imageRealm") imageCacheDb: IImageCacheDb): ImageCache {
        return ImageCache(imageCacheDb, AndroidSchedulers.mainThread())
    }

    @Named("room")
    @Singleton
    @Provides
    fun roomCach(): ICache {
        return RoomCache()
    }

    @Named("realm")
    @Singleton
    @Provides
    fun realmCach(): ICache {
        return RealmCache()
    }

    @Named("paper")
    @Singleton
    @Provides
    fun paperCach(): ICache {
        return PaperCache()
    }

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

    @Named("imagePaper")
    @Singleton
    @Provides
    fun paperImageCache(): IImageCacheDb {
        return PaperImageCache()
    }
}
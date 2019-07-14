package ru.vlabum.android.gb.androidretrofit.di

import dagger.Module
import dagger.Provides
import ru.vlabum.android.gb.androidretrofit.mvp.model.api.IDataSource
import ru.vlabum.android.gb.androidretrofit.mvp.model.api.INetworkStatus
import ru.vlabum.android.gb.androidretrofit.mvp.model.cache.ICache
import ru.vlabum.android.gb.androidretrofit.mvp.model.repo.GitHubRepo
import ru.vlabum.android.gb.androidretrofit.mvp.model.repo.IGitHubRepo
import javax.inject.Named

@Module(includes = [CacheModule::class, ApiModule::class])
class RepoModule {

    @Provides
    fun gitHubRepo(dataSource: IDataSource, networkStatus: INetworkStatus, @Named("room") cache: ICache): IGitHubRepo {
        return GitHubRepo(dataSource, networkStatus, cache)
    }

}
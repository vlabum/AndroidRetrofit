package ru.vlabum.android.gb.androidretrofit.di

import dagger.Module
import dagger.Provides
import ru.vlabum.android.gb.androidretrofit.mvp.model.api.INetworkStatus
import ru.vlabum.android.gb.androidretrofit.ui.NetworkStatus
import javax.inject.Singleton

@Module
class UtilsModule {

    @Singleton
    @Provides
    fun networkStatus(): INetworkStatus {
        return NetworkStatus()
    }
}

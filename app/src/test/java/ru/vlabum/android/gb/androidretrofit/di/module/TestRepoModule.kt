package ru.vlabum.android.gb.androidretrofit.di.module

import dagger.Module
import dagger.Provides
import org.mockito.Mockito
import ru.vlabum.android.gb.androidretrofit.mvp.model.repo.IGitHubRepo
import javax.inject.Singleton

@Module
open class TestRepoModule {

    @Singleton
    @Provides
    open fun gitHubRepo(): IGitHubRepo {
        return Mockito.mock(IGitHubRepo::class.java)
    }
}
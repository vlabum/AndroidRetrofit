package ru.vlabum.android.gb.androidretrofit.di

import dagger.Component
import ru.vlabum.android.gb.androidretrofit.UserRepoInstrumentedTest
import javax.inject.Singleton


@Singleton
@Component(modules = [RepoModule::class, UtilsModule::class, ApiModule::class, CacheModule::class])
interface TestComponentInst {
    fun inject(userRepoInstrumentedTest: UserRepoInstrumentedTest)
}
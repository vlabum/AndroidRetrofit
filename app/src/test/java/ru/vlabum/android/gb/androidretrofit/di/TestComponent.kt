package ru.vlabum.android.gb.androidretrofit.di

import dagger.Component
import ru.vlabum.android.gb.androidretrofit.MainPresenterTest
import ru.vlabum.android.gb.androidretrofit.di.module.TestRepoModule
import ru.vlabum.android.gb.androidretrofit.mvp.presenter.MainPresenter
import javax.inject.Singleton

@Singleton
@Component(modules = [TestRepoModule::class])
open interface TestComponent {
    fun inject(presenter: MainPresenter)
    fun inject(presenterTest: MainPresenterTest)
}
package ru.vlabum.android.gb.androidretrofit.mvp.model.repo

import io.reactivex.Single
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.IRepository
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.IUser

interface IGitHubRepo {

    fun getUser(username: String): Single<IUser>

    fun getUserRepos(user: IUser): Single<List<IRepository>>

}
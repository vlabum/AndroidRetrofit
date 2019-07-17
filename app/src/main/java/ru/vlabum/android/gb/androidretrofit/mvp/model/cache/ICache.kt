package ru.vlabum.android.gb.androidretrofit.mvp.model.cache

import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.IRepository
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.IUser

open interface ICache {
    open fun getUser(username: String): IUser?
    open fun saveUser(user: IUser)
    open fun getUserRepos(user: IUser): List<IRepository>?
    open fun saveUserRepos(user: IUser, repos: List<IRepository>)
}
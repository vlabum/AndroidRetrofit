package ru.vlabum.android.gb.androidretrofit.mvp.model.cache

import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.IRepository
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.IUser

interface ICache {
    fun getUser(username: String): IUser?
    fun saveUser(user: IUser)
    fun getUserRepos(user: IUser): List<IRepository>?
    fun saveUserRepos(user: IUser, repos: List<IRepository>)
}
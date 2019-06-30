package ru.vlabum.android.gb.androidretrofit.mvp.model.repo

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.vlabum.android.gb.androidretrofit.mvp.model.api.ApiHolder
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.User

class UsersRepo {
    fun getUser(username: String): Single<User> {
        return ApiHolder.getApi().getUser(username).subscribeOn(Schedulers.io())
    }
}
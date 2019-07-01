package ru.vlabum.android.gb.androidretrofit.mvp.model.repo

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.vlabum.android.gb.androidretrofit.mvp.model.api.ApiHolder
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.Repository

class RepositoriesRepo {
    fun getUserRepos(url: String): Single<List<Repository>> {
        return ApiHolder.getApi().getUserRepos(url).subscribeOn(Schedulers.io())
    }
}
package ru.vlabum.android.gb.androidretrofit.mvp.model.repo

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.vlabum.android.gb.androidretrofit.mvp.model.api.ApiHolder
import ru.vlabum.android.gb.androidretrofit.mvp.model.api.INetworkStatus
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.IRepository
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.IUser
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.User
import ru.vlabum.android.gb.androidretrofit.ui.NetworkStatus

class GitHubRepo {

    internal var networkStatus: INetworkStatus = NetworkStatus()

    private var cache: ICache? = null

    fun setCache(cache: ICache) {
        this.cache = cache
    }

    fun getUser(username: String): Single<IUser> {
        if (networkStatus.isOnline()) {
            return ApiHolder.getApi().getUser(username).subscribeOn(Schedulers.io())
                .map { user ->
                    cache?.saveUser(user)
                    user
                }
        } else {
            return Single.create<IUser> { emitter ->
                val user: IUser? = cache?.getUser(username)
                if (user == null) {
                    emitter.onError(RuntimeException("No such user in cache"))
                } else {
                    emitter.onSuccess(User(user.getLogin(), user.getAvatarUrl(), user.getReposUrl()))
                }

            }.subscribeOn(Schedulers.io())
        }
    }

    fun getUserRepos(user: IUser): Single<List<IRepository>> {
        if (networkStatus.isOnline()) {
            return ApiHolder.getApi().getUserRepos(user.getReposUrl()).subscribeOn(Schedulers.io())
                .map { repos ->
                    cache?.saveUserRepos(user, repos)
                    repos
                }
        } else {
            return Single.create { emitter ->
                val userInner: IUser? = cache?.getUser(user.getLogin())
                if (userInner == null) {
                    emitter.onError(java.lang.RuntimeException("No such user in cache"))
                } else {
                    val repos: List<IRepository>? = cache?.getUserRepos(userInner)
                    repos?.let {
                        emitter.onSuccess(it)
                    }
                }
            }
        }
    }
}
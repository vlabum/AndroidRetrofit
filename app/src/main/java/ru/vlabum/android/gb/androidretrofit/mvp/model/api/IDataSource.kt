package ru.vlabum.android.gb.androidretrofit.mvp.model.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.Repository
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.User

interface IDataSource {
    @GET("/users/{user}")
    fun getUser(@Path("user") username: String): Single<User>

    @GET
    fun getUserRepos(@Url url: String): Single<List<Repository>>
}
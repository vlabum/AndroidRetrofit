package ru.vlabum.android.gb.androidretrofit.mvp.model.entity

import com.google.gson.annotations.Expose

class User {

    @Expose
    private lateinit var login: String

    @Expose
    private var avatarUrl: String? = null

    @Expose
    private lateinit var reposUrl: String

    fun getLogin() = login

    fun setLogin(login: String) {
        this.login = login
    }

    fun getAvatarUrl() = avatarUrl

    fun setAvatarUrl(url: String) {
        this.avatarUrl = url
    }

    fun getReposUrl() = reposUrl

    fun setReposUrl(url: String) {
        this.reposUrl = url
    }

}
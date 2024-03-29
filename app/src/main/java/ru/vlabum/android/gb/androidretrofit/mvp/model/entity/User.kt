package ru.vlabum.android.gb.androidretrofit.mvp.model.entity

import com.google.gson.annotations.Expose

class User() : IUser {

    @Expose
    private lateinit var login: String

    @Expose
    private lateinit var avatarUrl: String

    @Expose
    private lateinit var reposUrl: String

    constructor(login: String, avatarUrl: String, reposUrl: String) : this() {
        this.login = login
        this.avatarUrl = avatarUrl
        this.reposUrl = reposUrl
    }

    override fun getLogin() = login

    override fun setLogin(login: String) {
        this.login = login
    }

    override fun getAvatarUrl() = avatarUrl

    override fun setAvatarUrl(avatarUrl: String) {
        this.avatarUrl = avatarUrl
    }

    override fun getReposUrl() = reposUrl

    override fun setReposUrl(reposUrl: String) {
        this.reposUrl = reposUrl
    }

}
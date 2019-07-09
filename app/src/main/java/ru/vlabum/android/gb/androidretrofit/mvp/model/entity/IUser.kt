package ru.vlabum.android.gb.androidretrofit.mvp.model.entity

interface IUser {

    fun getLogin(): String

    fun setLogin(login: String)

    fun getAvatarUrl(): String

    fun setAvatarUrl(avatarUrl: String)

    fun getReposUrl(): String

    fun setReposUrl(reposUrl: String)

}
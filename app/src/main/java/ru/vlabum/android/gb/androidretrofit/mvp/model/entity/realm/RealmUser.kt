package ru.vlabum.android.gb.androidretrofit.mvp.model.entity.realm

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.IUser

open class RealmUser() : RealmObject(), IUser {

    @PrimaryKey
    private lateinit var login: String
    private lateinit var avatarUrl: String
    private lateinit var reposUrl: String

    constructor(login: String, avatarUrl: String, reposUrl: String) : this() {
        this.login = login
        this.avatarUrl = avatarUrl
        this.reposUrl = reposUrl
    }

    private var repos = RealmList<RealmRepository>()

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

    fun getRepos(): RealmList<RealmRepository> {
        return repos
    }

    fun setRepos(repos: RealmList<RealmRepository>) {
        this.repos = repos
    }

}
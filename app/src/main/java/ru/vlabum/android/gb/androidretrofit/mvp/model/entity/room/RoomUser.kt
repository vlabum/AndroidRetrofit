package ru.vlabum.android.gb.androidretrofit.mvp.model.entity.room

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.IUser

@Entity
class RoomUser() : IUser {

    @NonNull
    @PrimaryKey
    private lateinit var login: String
    private lateinit var avatarUrl: String
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
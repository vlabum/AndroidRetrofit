package ru.vlabum.android.gb.androidretrofit.mvp.model.cache

import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.IRepository
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.IUser
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.room.RoomRepository
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.room.RoomUser
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.room.db.Database
import java.util.*

class RoomCache : ICache {

    override fun getUser(username: String): IUser? {
        return Database.getInstance().getUserDao()
            .findByLogin(username)
    }

    override fun saveUser(user: IUser) {
        var roomUser: RoomUser? = Database.getInstance().getUserDao()
            .findByLogin(user.getLogin())

        if (roomUser == null) {
            roomUser = RoomUser()
            roomUser.setLogin(user.getLogin())
        }

        roomUser.setAvatarUrl(user.getAvatarUrl())
        roomUser.setReposUrl(user.getReposUrl())

        Database.getInstance().getUserDao()
            .insert(roomUser)
    }

    override fun getUserRepos(user: IUser): List<IRepository>? {
        val roomUser: RoomUser? = Database.getInstance().getUserDao()
            .findByLogin(user.getLogin())

        if (roomUser == null) {
            return arrayListOf()
        } else {
            val roomRepositories = Database.getInstance().getRepositoryDao().findForUser(user.getLogin())
            return roomRepositories
        }
    }

    override fun saveUserRepos(user: IUser, repos: List<IRepository>) {
        var roomUser = Database.getInstance().getUserDao()
            .findByLogin(user.getLogin())

        if (roomUser == null) {
            roomUser = RoomUser()
            roomUser.setLogin(user.getLogin())
            roomUser.setAvatarUrl(user.getAvatarUrl())
            roomUser.setReposUrl(user.getReposUrl())
            Database.getInstance()
                .getUserDao()
                .insert(roomUser)

        }

        if (!repos.isEmpty()) {
            val roomRepositories = ArrayList<RoomRepository>()
            for (repository in repos) {
                val roomRepository = RoomRepository(repository.getId(), repository.getName(), user.getLogin())
                roomRepositories.add(roomRepository)
            }
            Database.getInstance()
                .getRepositoryDao()
                .insert(roomRepositories)
        }
    }
}
package ru.vlabum.android.gb.androidretrofit.ui.cache

import io.realm.Realm
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.IRepository
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.IUser
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.realm.RealmRepository
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.realm.RealmUser
import ru.vlabum.android.gb.androidretrofit.mvp.model.repo.ICache
import java.util.*

class RealmCache : ICache {

    override fun getUser(username: String): IUser? {
        val realm = Realm.getDefaultInstance()
        val realmUser = realm.where(RealmUser::class.java).equalTo("login", username).findFirst()
//        realm.close()
        if (realmUser == null) {
            return null
        }
        val user: IUser = RealmUser(realmUser.getLogin(), realmUser.getAvatarUrl(), realmUser.getReposUrl())
        return user
    }

    override fun saveUser(user: IUser) {
        val realm = Realm.getDefaultInstance()
        val realmUser = realm.where(RealmUser::class.java).equalTo("login", user.getLogin()).findFirst()
        if (realmUser == null) {
            realm.executeTransaction { innerRealm ->
                val newRealmUser = innerRealm.createObject(RealmUser::class.java, user.getLogin())
                newRealmUser.setAvatarUrl(user.getAvatarUrl())
                newRealmUser.setReposUrl(user.getReposUrl())
            }
        } else {
            realm.executeTransaction { innerRealm ->
                realmUser.setAvatarUrl(user.getAvatarUrl())
                realmUser.setReposUrl(user.getReposUrl())
            }
        }
        realm.close()
    }

    override fun getUserRepos(user: IUser): List<IRepository>? {
        val realm = Realm.getDefaultInstance()
        val realmUser = realm.where(RealmUser::class.java).equalTo("login", user.getLogin()).findFirst()

        if (realmUser == null) {
            return arrayListOf()
        } else {
            val realmRepos = realmUser.getRepos()
            val repos = ArrayList<IRepository>()
            for (realmRepository in realmRepos) {
                repos.add(RealmRepository(realmRepository.getId(), realmRepository.getName()))
            }
            return repos
        }
    }

    override fun saveUserRepos(user: IUser, repos: List<IRepository>) {
        val realm = Realm.getDefaultInstance()
        val realmUser = realm.where(RealmUser::class.java).equalTo("login", user.getLogin()).findFirst()
        if (realmUser == null) {
            realm.executeTransaction { innerRealm ->
                val newRealmUser = innerRealm.createObject(RealmUser::class.java, user.getLogin())
                newRealmUser.setAvatarUrl(user.getAvatarUrl())
                newRealmUser.setReposUrl(user.getReposUrl())
            }
        }

        realm.executeTransaction { innerRealm ->
            realmUser!!.getRepos().deleteAllFromRealm()
            for (repository in repos) {
                val realmRepository = innerRealm.createObject(RealmRepository::class.java, repository.getId())
                realmRepository.setName(repository.getName())
                realmUser.getRepos().add(realmRepository)
            }
        }
        realm.close()
    }
}
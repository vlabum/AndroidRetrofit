package ru.vlabum.android.gb.androidretrofit.mvp.model.cache

//import io.paperdb.Paper

//class PaperCache : ICache {
//
//    companion object {
//        private val usersBook = "users"
//        private val reposBook = "repos"
//    }
//
//    override fun getUser(username: String): IUser? {
//        val path = Paper.book(usersBook).path
//        val user: IUser? = Paper.book(usersBook).read<User>(username)
//        return user
//    }
//
//    override fun saveUser(user: IUser) {
//        Paper.book(usersBook).write(user.getLogin(), user)
//    }
//
//    override fun getUserRepos(user: IUser): List<IRepository>? {
//        return Paper.book(reposBook).read<List<Repository>>(user.getLogin())
//    }
//
//    override fun saveUserRepos(user: IUser, repos: List<IRepository>) {
//        Paper.book(reposBook).write(user.getLogin(), repos)
//    }
//}
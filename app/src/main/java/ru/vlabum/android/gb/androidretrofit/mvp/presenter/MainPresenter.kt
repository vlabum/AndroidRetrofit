package ru.vlabum.android.gb.androidretrofit.mvp.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.Scheduler
import io.reactivex.subjects.PublishSubject
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.Repository
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.User
import ru.vlabum.android.gb.androidretrofit.mvp.model.repo.RepositoriesRepo
import ru.vlabum.android.gb.androidretrofit.mvp.model.repo.UsersRepo
import ru.vlabum.android.gb.androidretrofit.mvp.view.IMainView
import ru.vlabum.android.gb.androidretrofit.mvp.view.IRepositoryRowView

@InjectViewState
class MainPresenter(val mainThreadScheduler: Scheduler) : MvpPresenter<IMainView>() {

    class RepositoryListPresenter : IRepositoryListPresenter {

        var clickSubjectVar = PublishSubject.create<IRepositoryRowView>()
        var repos: ArrayList<Repository> = arrayListOf()

        override fun bind(viewI: IRepositoryRowView) {
            viewI.setName(repos[viewI.getPos()].getName())
        }

        override fun getCount(): Int {
            return repos.count()
        }

        override fun getClickSubject(): PublishSubject<IRepositoryRowView> {
            return clickSubjectVar
        }

    }

    private var repositoriesRepo: RepositoriesRepo
    private var usersRepo: UsersRepo
    //    private var mainThreadScheduler: Scheduler
    private var repositoryListPresenter: RepositoryListPresenter

    init {
        this.repositoriesRepo = RepositoriesRepo()
        this.usersRepo = UsersRepo()
//        this.mainThreadScheduler = mainThreadScheduler
        repositoryListPresenter = RepositoryListPresenter()
    }

    fun getRepositoryListPresenter() = repositoryListPresenter

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadUsers()

        val d = repositoryListPresenter.getClickSubject().subscribe { repoRowView ->
            viewState.showMessage(repositoryListPresenter.repos[repoRowView.getPos()].getName())
        }
    }

    private fun loadUsers() {
        viewState.showLoading()
        val d = usersRepo.getUser("googlesamples")
            .observeOn(mainThreadScheduler)
            .subscribe { user ->
                loadUserRepos(user)
                viewState.hideLoading()
                viewState.setUsername(user.getLogin())
                viewState.loadImage(user.getAvatarUrl())
            }
    }

    private fun loadUserRepos(user: User) {
        viewState.showLoading()
        val d = repositoriesRepo.getUserRepos(user.getReposUrl())
            .observeOn(mainThreadScheduler)
            .subscribe { repositories ->
                repositoryListPresenter.repos.clear()
                repositoryListPresenter.repos.addAll(repositories)
                viewState.updateList()
                viewState.hideLoading()
            }
    }
}
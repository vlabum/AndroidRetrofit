package ru.vlabum.android.gb.androidretrofit.mvp.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.IRepository
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.IUser
import ru.vlabum.android.gb.androidretrofit.mvp.model.repo.GitHubRepo
import ru.vlabum.android.gb.androidretrofit.mvp.model.repo.ICache
import ru.vlabum.android.gb.androidretrofit.mvp.view.IMainView
import ru.vlabum.android.gb.androidretrofit.mvp.view.IRepositoryRowView

@InjectViewState
class MainPresenter(val mainThreadScheduler: Scheduler, val cache: ICache) : MvpPresenter<IMainView>() {

    class RepositoryListPresenter : IRepositoryListPresenter {

        var clickSubjectVar = PublishSubject.create<IRepositoryRowView>()
        var repos: ArrayList<IRepository> = arrayListOf()

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

    private var gitHubRepo: GitHubRepo
    private var repositoryListPresenter: RepositoryListPresenter

    init {
        gitHubRepo = GitHubRepo()
        gitHubRepo.setCache(cache)
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
        val d = gitHubRepo.getUser("googlesamples")
            .subscribeOn(Schedulers.io())
            .observeOn(mainThreadScheduler)
            .subscribe { user ->
                loadUserRepos(user)
                viewState.hideLoading()
                viewState.setUsername(user.getLogin())
                viewState.loadImage(user.getAvatarUrl())
            }
    }

    private fun loadUserRepos(user: IUser) {
        viewState.showLoading()
        val d = gitHubRepo.getUserRepos(user)
            .subscribeOn(Schedulers.io())
            .observeOn(mainThreadScheduler)
            .subscribe { repositories ->
                repositoryListPresenter.repos.clear()
                repositoryListPresenter.repos.addAll(repositories)
                viewState.updateList()
                viewState.hideLoading()
            }
    }
}
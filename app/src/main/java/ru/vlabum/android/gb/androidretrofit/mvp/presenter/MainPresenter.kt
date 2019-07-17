package ru.vlabum.android.gb.androidretrofit.mvp.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.IRepository
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.IUser
import ru.vlabum.android.gb.androidretrofit.mvp.model.repo.IGitHubRepo
import ru.vlabum.android.gb.androidretrofit.mvp.view.IMainView
import ru.vlabum.android.gb.androidretrofit.mvp.view.IRepositoryRowView
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
open class MainPresenter(val mainThreadScheduler: Scheduler) : MvpPresenter<IMainView>() {

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

    @Inject
    lateinit var gitHubRepo: IGitHubRepo

    private var repositoryListPresenter: RepositoryListPresenter

    init {
//        gitHubRepo = GitHubRepo()
//        gitHubRepo.setCache(cache)
        repositoryListPresenter = RepositoryListPresenter()
    }

    fun getRepositoryListPresenter() = repositoryListPresenter

    override public fun onFirstViewAttach() {
        super.onFirstViewAttach()
        Timber.d("onFirstViewAttach")
        getViewState().init()
        loadUsers("googlesamples")

        val d = repositoryListPresenter.getClickSubject().subscribe { repoRowView ->
            viewState.showMessage(repositoryListPresenter.repos[repoRowView.getPos()].getName())
        }
    }

    fun loadUsers(userName: String) {
        Timber.d("lllllloooooaaaaad")
        viewState.showLoading()
        val d = gitHubRepo.getUser(userName)
            .subscribeOn(Schedulers.io())
            .observeOn(mainThreadScheduler)
            .subscribe { user ->
                loadUserRepos(user)
                viewState.setUsername(user.getLogin())
                viewState.loadImage(user.getAvatarUrl())
                viewState.hideLoading()
            }
    }

    fun loadUserRepos(user: IUser) {
        val d = gitHubRepo.getUserRepos(user)
            .subscribeOn(Schedulers.io())
            .observeOn(mainThreadScheduler)
            .subscribe { repositories ->
                repositoryListPresenter.repos.clear()
                repositoryListPresenter.repos.addAll(repositories)
                viewState.updateList()
            }
    }
}
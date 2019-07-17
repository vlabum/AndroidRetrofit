package ru.vlabum.android.gb.androidretrofit

import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import ru.vlabum.android.gb.androidretrofit.di.DaggerTestComponent
import ru.vlabum.android.gb.androidretrofit.di.TestComponent
import ru.vlabum.android.gb.androidretrofit.di.module.TestRepoModule
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.IRepository
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.IUser
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.User
import ru.vlabum.android.gb.androidretrofit.mvp.model.repo.IGitHubRepo
import ru.vlabum.android.gb.androidretrofit.mvp.presenter.MainPresenter
import ru.vlabum.android.gb.androidretrofit.mvp.view.IMainView
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainPresenterTest {

    private lateinit var presenter: MainPresenter
    private lateinit var testScheduler: TestScheduler

    @Mock
    lateinit var mainView: IMainView

    @Inject
    lateinit var gitHubRepo: IGitHubRepo

    companion object {

        @BeforeClass
        @JvmStatic
        fun setupClass() {
            Timber.plant(Timber.DebugTree())
            Timber.d("setupClass")
        }

        @AfterClass
        @JvmStatic
        fun tearDownClass() {
            Timber.d("tearDownClass")
        }

    }

    @Before
    fun setup() {
        Timber.d("setup")
        MockitoAnnotations.initMocks(this)
        testScheduler = TestScheduler()
        presenter = Mockito.spy(MainPresenter(testScheduler))
    }

    @After
    fun tearDown() {
        Timber.d("tearDown")
    }

    @Test
    fun loadDataSuccess() {
        val user: User = User("googlesamples", "avatarUrl", "reposUrl")
        val component = getTestComponentUser(user)

        component.inject(this)
        component.inject(presenter)

        presenter.attachView(mainView)
        Mockito.verify(mainView).init()
//        Mockito.verify(presenter, Mockito.times(1)).loadUsers("googlesamples")

        //пусть будто-бы пройдет 1 секунда
        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS)

        Mockito.verify(mainView, Mockito.times(1)).showLoading()
        Mockito.verify(mainView, Mockito.times(1)).hideLoading()
        Mockito.verify(gitHubRepo).getUser(user.getLogin())

        Mockito.verify(mainView).setUsername(user.getLogin())
        Mockito.verify(mainView).loadImage(user.getAvatarUrl())
        Mockito.verify(gitHubRepo).getUserRepos(user)
        Mockito.verify(mainView).updateList()

    }


    @Test(expected = NullPointerException::class)
    fun loadDataFailure() {
        val user: User = User("googl", "avatarUrl", "reposUrl")
        val component = getTestComponentUser(user)

        component.inject(this)
        component.inject(presenter)

        presenter.attachView(mainView)
        Mockito.verify(mainView).init()

        //пусть будто-бы пройдет 1 секунда
        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS)

        Mockito.verify(mainView, Mockito.times(1)).showLoading()
        Mockito.verify(mainView, Mockito.times(1)).hideLoading()
        Mockito.verify(gitHubRepo).getUser(user.getLogin())

        Mockito.verify(mainView).setUsername(user.getLogin())
        Mockito.verify(mainView).loadImage(user.getAvatarUrl())
        Mockito.verify(gitHubRepo).getUserRepos(user)
        Mockito.verify(mainView).updateList()
    }

    private fun getTestComponentUser(user: IUser): TestComponent {
        return DaggerTestComponent.builder()
            .testRepoModule(object : TestRepoModule() {
                override fun gitHubRepo(): IGitHubRepo {
                    val repo: IGitHubRepo = super.gitHubRepo()
                    Mockito.`when`(repo.getUser(user.getLogin())).thenReturn(Single.just(user))
                    Mockito.`when`(repo.getUserRepos(user)).thenReturn(Single.just(ArrayList<IRepository>()))
                    return repo
                }
            })
            .build()
    }

}
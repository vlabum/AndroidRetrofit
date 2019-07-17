package ru.vlabum.android.gb.androidretrofit


import io.reactivex.observers.TestObserver
import junit.framework.TestCase.assertEquals
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.*
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import ru.vlabum.android.gb.androidretrofit.di.ApiModule
import ru.vlabum.android.gb.androidretrofit.di.CacheModule
import ru.vlabum.android.gb.androidretrofit.di.DaggerTestComponentInst
import ru.vlabum.android.gb.androidretrofit.mvp.model.cache.ICache
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.IRepository
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.IUser
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.Repository
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.User
import ru.vlabum.android.gb.androidretrofit.mvp.model.repo.IGitHubRepo
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject


open class UserRepoInstrumentedTest {

    @Inject
    lateinit var usersRepo: IGitHubRepo

    //    @Inject
    @Mock
    lateinit var cache: ICache

    companion object {

        private var mockWebServer: MockWebServer? = null

        @BeforeClass
        @JvmStatic
        @Throws(IOException::class)
        fun setupClass() {
            Timber.d("setupClass")
            Timber.plant(Timber.DebugTree())
            mockWebServer = MockWebServer()
            mockWebServer!!.start()
        }

        @AfterClass
        @JvmStatic
        @Throws(IOException::class)
        fun tearDownClass() {
            Timber.d("tearDownClass")
            mockWebServer!!.shutdown()
        }
    }

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Timber.d("setup")
        val component = DaggerTestComponentInst
            .builder()
            .cacheModule(object : CacheModule() {
                override fun roomCach(): ICache {
                    return cache
                }
            })
            .apiModule(object : ApiModule() {
                override fun baseUrl(): String {
                    return mockWebServer!!.url("/").toString()
                }
            }).build()
        component.inject(this)
    }

    @After
    fun tearDown() {
        Timber.d("tearDown")
    }


    @Test
    fun getUser() {
        mockWebServer!!.enqueue(createUserResponse("login", "avatarUrl", "reposUrl"))
        val observer = TestObserver<IUser>()
        usersRepo.getUser("login").subscribe(observer)

        observer.awaitTerminalEvent()

        observer.assertValueCount(1)
        assertEquals(observer.values().get(0).getLogin(), "login")
        assertEquals(observer.values().get(0).getAvatarUrl(), "avatarUrl")
        assertEquals(observer.values().get(0).getReposUrl(), "reposUrl")
    }


    @Test
    fun getUserRepos() {
        mockWebServer!!.enqueue(createUserRepoResponse())
        val user = User("googlesamples", "avatarUrl", "reposUrl")
        val observer = TestObserver<List<IRepository>>()
        usersRepo.getUserRepos(user).subscribe(observer)

        val repos: List<IRepository> = arrayListOf(
            Repository("100560557", "account-transfer-api"),
            Repository("66757521", "android-AccelerometerPlay")
        )

        observer.awaitTerminalEvent()

        observer.assertValueCount(1)
        assertEquals(observer.values().get(0).get(0).getName(), "account-transfer-api")
        assertEquals(observer.values().get(0).get(1).getName(), "android-AccelerometerPlay")
        assertEquals(observer.values().get(0).get(0).getId(), "100560557")
        assertEquals(observer.values().get(0).get(1).getId(), "66757521")
    }


    private fun createUserResponse(login: String, avatarUrl: String, reposUrl: String): MockResponse {
        val body = "{\"login\":\"$login\", \"avatar_url\":\"$avatarUrl\", \"repos_url\":\"$reposUrl\"}"
        return MockResponse().setBody(body)
    }

    private fun createUserRepoResponse(): MockResponse {
        val body = "[" +
                "{" +
                "\"id\": 100560557," +
                "\"node_id\": \"MDEwOlJlcG9zaXRvcnkxMDA1NjA1NTc=\"," +
                "\"name\": \"account-transfer-api\"," +
                "\"full_name\": \"googlesamples/account-transfer-api\"," +
                "\"private\": false," +
                "\"forks\": 11," +
                "\"open_issues\": 0," +
                "\"watchers\": 15," +
                "\"default_branch\": \"master\"" +
                "}," +
                "{" +
                "\"id\": 66757521," +
                "\"node_id\": \"MDEwOlJlcG9zaXRvcnk2Njc1NzUyMQ==\"," +
                "\"name\": \"android-AccelerometerPlay\"," +
                "\"full_name\": \"googlesamples/android-AccelerometerPlay\"," +
                "\"private\": false," +
                "\"forks\": 66," +
                "\"open_issues\": 4," +
                "\"watchers\": 102," +
                "\"default_branch\": \"master\"" +
                "}" +
                "]"
        return MockResponse().setBody(body)
    }

}

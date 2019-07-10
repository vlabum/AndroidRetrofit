package ru.vlabum.android.gb.androidretrofit.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import ru.vlabum.android.gb.androidretrofit.App
import ru.vlabum.android.gb.androidretrofit.R
import ru.vlabum.android.gb.androidretrofit.mvp.presenter.MainPresenter
import ru.vlabum.android.gb.androidretrofit.mvp.view.IMainView
import ru.vlabum.android.gb.androidretrofit.ui.adapter.ReposRVAdapter
import ru.vlabum.android.gb.androidretrofit.ui.image.GlideImageLoader

class MainActivity : MvpAppCompatActivity(), IMainView {

    companion object {
        val PERMISSION_INTERTET = 199
    }

    @InjectPresenter
    lateinit var presenter: MainPresenter

    lateinit var adapter: ReposRVAdapter

    val imageLoader = GlideImageLoader()

//    val piperCache: ICache = PaperCache()
//    val roomCache: ICache = RoomCache()
//    val realmCache: ICache = RealmCache()

    @ProvidePresenter
    fun providePresenter(): MainPresenter {
        val presenter = MainPresenter(AndroidSchedulers.mainThread())
        App.getInstance().getAppComponent().inject(presenter)
        return presenter
    }

    override fun init() {
        rv.layoutManager = LinearLayoutManager(this)
        adapter = ReposRVAdapter(presenter.getRepositoryListPresenter())
        rv.adapter = adapter
        App.getInstance().getAppComponent().inject(imageLoader)
    }

    override fun updateList() {
        adapter.notifyDataSetChanged()
    }

    override fun showLoading() {
        rl_loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        rl_loading.visibility = View.GONE
    }

    override fun setUsername(name: String) {
        tv_username.text = name
    }

    override fun loadImage(url: String?) {
        url?.let { imageLoader.loadInto(url, iv_avatar) }
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun reqPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS), PERMISSION_INTERTET)
        }
    }

}

package ru.vlabum.android.gb.androidretrofit

import android.app.Application
import io.paperdb.Paper
import io.realm.Realm
import io.realm.RealmConfiguration
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.room.db.Database
import timber.log.Timber

class App : Application() {

    companion object {
        private lateinit var instance: App
        fun getInstance(): App {
            return instance
        }
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
        Timber.plant(Timber.DebugTree())
        Timber.d("start")

        Paper.init(this)

        Database.create(this)

        Realm.init(this)
        val config = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(config)
    }

}
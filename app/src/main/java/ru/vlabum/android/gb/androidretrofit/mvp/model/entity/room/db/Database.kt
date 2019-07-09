package ru.vlabum.android.gb.androidretrofit.mvp.model.entity.room.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.room.RoomRepository
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.room.RoomUser
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.room.dao.RepositoryDao
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.room.dao.UserDao

@androidx.room.Database(entities = [RoomUser::class, RoomRepository::class], version = 1)
abstract class Database : RoomDatabase() {

    companion object {

        private val DB_NAME = "database.db"

        @Volatile
        private lateinit var instance: Database

        fun create(context: Context) {
            instance = Room.databaseBuilder(
                context.applicationContext,
                Database::class.java,
                DB_NAME
            ).build()
        }

        @Synchronized
        fun getInstance(): Database {
            if (instance == null) {
                throw RuntimeException("Database has not been created. Please call create()")
            }
            return instance
        }

    }

    abstract fun getUserDao(): UserDao

    abstract fun getRepositoryDao(): RepositoryDao

}
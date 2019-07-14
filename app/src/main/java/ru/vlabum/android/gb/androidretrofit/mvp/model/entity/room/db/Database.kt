package ru.vlabum.android.gb.androidretrofit.mvp.model.entity.room.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.room.RoomImage
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.room.RoomRepository
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.room.RoomUser
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.room.dao.ImageDao
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.room.dao.RepositoryDao
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.room.dao.UserDao

@androidx.room.Database(entities = [RoomUser::class, RoomRepository::class, RoomImage::class], version = 2)
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
            )
                .addMigrations(object : Migration(1, 2) {
                    override fun migrate(database: SupportSQLiteDatabase) {
                        database.execSQL("CREATE TABLE RoomImage (url text primary key not null, path text not null)")
                        return
                    }

                })
                .build()
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

    abstract fun getImageDao(): ImageDao

}
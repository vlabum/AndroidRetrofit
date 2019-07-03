package ru.vlabum.android.gb.androidretrofit.mvp.model.entity.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.room.RoomRepository

@Dao
interface RepositoryDao {
    @Insert(onConflict = REPLACE)
    abstract fun insert(repository: RoomRepository)

    @Insert(onConflict = REPLACE)
    abstract fun insert(vararg repository: RoomRepository)

    @Insert(onConflict = REPLACE)
    abstract fun insert(repository: List<RoomRepository>)

    @Insert
    abstract fun update(repository: RoomRepository)

    @Insert
    abstract fun update(vararg repository: RoomRepository)

    @Insert
    abstract fun update(repository: List<RoomRepository>)

    @Delete
    abstract fun delete(repository: RoomRepository)

    @Delete
    abstract fun delete(vararg repository: RoomRepository)

    @Delete
    abstract fun delete(repository: List<RoomRepository>)

    @Query("SELECT * FROM roomrepository")
    abstract fun getAll(): List<RoomRepository>

    @Query("SELECT * FROM roomrepository WHERE userLogin = :login")
    abstract fun findForUser(login: String): List<RoomRepository>
}
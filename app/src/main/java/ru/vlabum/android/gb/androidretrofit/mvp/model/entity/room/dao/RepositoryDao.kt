package ru.vlabum.android.gb.androidretrofit.mvp.model.entity.room.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.room.RoomRepository

@Dao
interface RepositoryDao {
    @Insert(onConflict = REPLACE)
    abstract fun insert(repository: RoomRepository)

    @Insert(onConflict = REPLACE)
    abstract fun insert(vararg repository: RoomRepository)

    @Insert(onConflict = REPLACE)
    abstract fun insert(repository: List<RoomRepository>)

    @Update
    abstract fun update(repository: RoomRepository)

    @Update
    abstract fun update(vararg repository: RoomRepository)

    @Update
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
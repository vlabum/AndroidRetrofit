package ru.vlabum.android.gb.androidretrofit.mvp.model.entity.room.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.room.RoomUser

@Dao
interface UserDao {

    @Insert(onConflict = REPLACE)
    abstract fun insert(user: RoomUser)

    @Insert(onConflict = REPLACE)
    abstract fun insert(vararg user: RoomUser)

    @Insert(onConflict = REPLACE)
    abstract fun insert(user: List<RoomUser>)

    @Update
    abstract fun update(user: RoomUser)

    @Update
    abstract fun update(vararg user: RoomUser)

    @Update
    abstract fun update(user: List<RoomUser>)

    @Delete
    abstract fun delete(user: RoomUser)

    @Delete
    abstract fun delete(vararg user: RoomUser)

    @Delete
    abstract fun delete(user: List<RoomUser>)

    @Query("SELECT * FROM roomuser")
    abstract fun getAll(): List<RoomUser>

    @Query("SELECT * FROM roomuser WHERE login = :login LIMIT 1")
    abstract fun findByLogin(login: String): RoomUser

}
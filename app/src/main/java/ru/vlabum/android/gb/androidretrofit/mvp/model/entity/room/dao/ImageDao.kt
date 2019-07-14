package ru.vlabum.android.gb.androidretrofit.mvp.model.entity.room.dao

import androidx.room.*
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.room.RoomImage

@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(image: RoomImage)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(vararg image: RoomImage)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(image: List<RoomImage>)

    @Update
    abstract fun update(image: RoomImage)

    @Update
    abstract fun update(vararg image: RoomImage)

    @Update
    abstract fun update(image: List<RoomImage>)

    @Delete
    abstract fun delete(image: RoomImage)

    @Delete
    abstract fun delete(vararg image: RoomImage)

    @Delete
    abstract fun delete(image: List<RoomImage>)

    @Query("SELECT * FROM roomimage")
    abstract fun getAll(): List<RoomImage>

    @Query("SELECT * FROM roomimage WHERE url = :url LIMIT 1")
    abstract fun findByUrl(url: String): RoomImage

    @Query("SELECT 1 as isExists FROM roomimage WHERE url = :url LIMIT 1")
    abstract fun contains(url: String): Int

    @Query("DELETE FROM roomimage")
    abstract fun clear()

}
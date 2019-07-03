package ru.vlabum.android.gb.androidretrofit.mvp.model.entity.room

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.IRepository

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomUser::class,
        parentColumns = ["login"], childColumns = ["userLogin"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )]
)
class RoomRepository() : IRepository {

    @NonNull
    @PrimaryKey
    private lateinit var id: String
    private lateinit var name: String
    private lateinit var userLogin: String

    constructor(id: String, name: String, userLogin: String) : this() {
        this.id = id
        this.name = name
        this.userLogin = userLogin
    }

    override fun getId() = id


    override fun setId(id: String) {
        this.id = id
    }

    override fun getName() = name

    override fun setName(name: String) {
        this.name = name
    }

    fun getUserLogin() = userLogin

    fun setUserLogin(userLogin: String) {
        this.userLogin = userLogin
    }

}
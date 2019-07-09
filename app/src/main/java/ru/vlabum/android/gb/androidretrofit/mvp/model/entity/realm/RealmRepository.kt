package ru.vlabum.android.gb.androidretrofit.mvp.model.entity.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.IRepository

open class RealmRepository() : RealmObject(), IRepository {

    @PrimaryKey
    private lateinit var id: String
    private lateinit var name: String

    constructor(id: String, name: String) : this() {
        this.id = id
        this.name = name
    }

    override fun getId() = id


    override fun setId(id: String) {
        this.id = id
    }

    override fun getName() = name


    override fun setName(name: String) {
        this.name = name
    }
}
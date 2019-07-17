package ru.vlabum.android.gb.androidretrofit.mvp.model.entity

import com.google.gson.annotations.Expose

class Repository : IRepository {

    @Expose
    private lateinit var name: String

    @Expose
    private lateinit var id: String

    constructor(id: String, name: String) {
        this.id = id
        this.name = name
    }

    override fun getName() = name

    override fun setName(name: String) {
        this.name = name
    }

    override fun getId() = id

    override fun setId(id: String) {
        this.id = id
    }

}
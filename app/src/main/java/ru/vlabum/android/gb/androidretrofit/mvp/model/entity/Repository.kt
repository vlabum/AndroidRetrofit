package ru.vlabum.android.gb.androidretrofit.mvp.model.entity

import com.google.gson.annotations.Expose

class Repository {
    @Expose
    private lateinit var name: String

    fun getName() = name

    fun setName(name: String) {
        this.name = name
    }
}
package ru.vlabum.android.gb.androidretrofit.mvp.model.entity

interface IRepository {

    fun getId(): String

    fun setId(id: String)

    fun getName(): String

    fun setName(name: String)

}
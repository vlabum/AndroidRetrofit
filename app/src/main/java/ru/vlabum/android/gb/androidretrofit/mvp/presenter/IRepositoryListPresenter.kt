package ru.vlabum.android.gb.androidretrofit.mvp.presenter

import io.reactivex.subjects.PublishSubject
import ru.vlabum.android.gb.androidretrofit.mvp.view.IRepositoryRowView

interface IRepositoryListPresenter {
    fun bind(viewI: IRepositoryRowView)
    fun getCount(): Int
    fun getClickSubject(): PublishSubject<IRepositoryRowView>
}
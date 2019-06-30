package ru.vlabum.android.gb.androidretrofit.mvp.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface IMainView : MvpView {
    fun init()
    fun updateList()

    fun showLoading()
    fun hideLoading()

    fun setUsername(name: String)
    fun loadImage(url: String?)

    fun showMessage(message: String)
}
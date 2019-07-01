package ru.vlabum.android.gb.androidretrofit.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding3.view.clicks
import kotlinx.android.synthetic.main.item_repo.view.*
import ru.vlabum.android.gb.androidretrofit.R
import ru.vlabum.android.gb.androidretrofit.mvp.presenter.IRepositoryListPresenter
import ru.vlabum.android.gb.androidretrofit.mvp.view.IRepositoryRowView

class ReposRVAdapter(var listRepos: IRepositoryListPresenter) : RecyclerView.Adapter<ReposRVAdapter.ViewHolderI>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderI {
        return ViewHolderI(LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false))
    }

    override fun getItemCount(): Int {
        return listRepos.getCount()
    }

    override fun onBindViewHolder(holder: ViewHolderI, position: Int) {
        holder.setPos(position)
        listRepos.bind(holder)
        holder.itemView.clicks().map { o -> holder }.subscribe(listRepos.getClickSubject())
//RxView
    }


    class ViewHolderI(itemView: View) : RecyclerView.ViewHolder(itemView), IRepositoryRowView {

        private var pos: Int = 0

        override fun getPos(): Int {
            return pos
        }

        fun setPos(pos: Int) {
            this.pos = pos
        }

        override fun setName(name: String) {
            itemView.tv_item_repo.text = name
        }
    }

}
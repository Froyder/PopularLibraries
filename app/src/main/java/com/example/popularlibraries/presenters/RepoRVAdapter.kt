package com.example.popularlibraries.presenters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.popularlibraries.databinding.ItemRepoBinding
import com.example.popularlibraries.presentations.RepoItemView

class RepoRVAdapter (
    private val presenter: IRepoListPresenter
)
    : RecyclerView.Adapter<RepoRVAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = presenter.bindView(holder.apply { pos = position })

    inner class ViewHolder(private val vb: ItemRepoBinding) : RecyclerView.ViewHolder(vb.root),
        RepoItemView {
        override fun setTitle(title: String) {
            vb.title.text = "Repo title: $title"
        }

        override var pos = -1

    }

}
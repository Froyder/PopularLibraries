package com.example.popularlibraries.presenters

import com.example.popularlibraries.presentations.IItemView
import com.example.popularlibraries.presentations.RepoItemView
import com.example.popularlibraries.presentations.UserItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

interface IUserListPresenter : IListPresenter<UserItemView>
interface IRepoListPresenter : IListPresenter<RepoItemView>
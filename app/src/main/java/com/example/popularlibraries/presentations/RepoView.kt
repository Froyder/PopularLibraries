package com.example.popularlibraries.presentations

import com.example.popularlibraries.data.UserRepo
import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState

interface RepoView : MvpView {
    @SingleState
    fun setRepoDetails (repo: UserRepo)
}
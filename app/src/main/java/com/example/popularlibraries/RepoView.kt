package com.example.popularlibraries

import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState

interface RepoView : MvpView {
    @SingleState
    fun setRepoDetails (repo: UserRepo)
}
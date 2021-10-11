package com.example.popularlibraries

import moxy.MvpView

import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(SingleStateStrategy::class)
interface UserDetailsView : MvpView {
    fun setUserPage(user : GithubUser)
    fun updateRepoList()
    fun onLoadingRepoListError(throwable: Throwable)
}
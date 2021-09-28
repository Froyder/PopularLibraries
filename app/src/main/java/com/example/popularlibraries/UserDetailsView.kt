package com.example.popularlibraries

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.SingleState

interface UserDetailsView : MvpView {
    @SingleState
    fun setUserData (user : GithubUser)
}
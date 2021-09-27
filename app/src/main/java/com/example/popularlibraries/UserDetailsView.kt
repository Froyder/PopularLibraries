package com.example.popularlibraries

import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState

interface UserDetailsView : MvpView {
    @SingleState
    fun setUserName (name : String)
}
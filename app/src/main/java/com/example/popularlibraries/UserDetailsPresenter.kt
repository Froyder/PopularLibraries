package com.example.popularlibraries

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserDetailsPresenter (private val router: Router, private val user: GithubUser) : MvpPresenter<UserDetailsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setUserName(user.login)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
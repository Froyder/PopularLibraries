package com.example.popularlibraries

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter

class UserDetailsPresenter (private val router: Router, private val user: GithubUser?) : MvpPresenter<UserDetailsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        user?.login?.let { viewState.setUserData(user) }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
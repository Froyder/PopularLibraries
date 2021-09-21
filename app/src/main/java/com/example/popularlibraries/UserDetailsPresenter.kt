package com.example.popularlibraries

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter

class UserDetailsPresenter (private val router: Router, val userID: Long) : MvpPresenter<UserDetailsView>() {

    private val usersRepo = GithubUsersRepo

    fun getUserData() {
        usersRepo
            .getUserDataObservable(userID)
            .subscribe({ user ->
                sendUserDataToFragment (user)
            }, { error ->
                println("Error: ${error.message}")
            })
    }

    private fun sendUserDataToFragment(user: GithubUser) {
        viewState.setUserData(user)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
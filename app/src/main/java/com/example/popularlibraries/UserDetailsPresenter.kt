package com.example.popularlibraries

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserDetailsPresenter (private val router: Router, val userID: Long) : MvpPresenter<UserDetailsView>() {

    private val usersRepo = GithubUsersRepo

    fun getUserData() {
            usersRepo
            .getUserDataObservable(userID)
            .doOnSubscribe { println("onSubscribe") }
            .doOnSuccess {user -> sendUserDataToFragment(user)}
            .doOnError { error -> println( "Error: $error") }
    }

    private fun sendUserDataToFragment(user: GithubUser) {
        viewState.setUserData(user)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
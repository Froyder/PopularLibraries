package com.example.popularlibraries.presenters

import com.example.popularlibraries.AndroidNetworkStatus
import com.example.popularlibraries.IScreens
import com.example.popularlibraries.data.GitHubDatabase
import com.example.popularlibraries.data.GithubUser
import com.example.popularlibraries.data.UserRepo
import com.example.popularlibraries.presentations.RepoView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class RepoDetailsPresenter (
    private val repo: UserRepo,
    private val user: GithubUser,
    private val router: Router,
    private val screens: IScreens
    ) : MvpPresenter<RepoView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setRepoDetails(repo)
    }

    fun backPressed(): Boolean {
        router.navigateTo(screens.userDetails(user, GitHubDatabase.getInstance()))
        return true
    }

}
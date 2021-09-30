package com.example.popularlibraries

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
        router.navigateTo(screens.userDetails(user))
        return true
    }

}
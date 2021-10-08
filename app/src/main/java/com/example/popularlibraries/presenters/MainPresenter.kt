package com.example.popularlibraries.presenters

import com.example.popularlibraries.AndroidNetworkStatus
import com.example.popularlibraries.IScreens
import com.example.popularlibraries.presentations.MainView
import com.github.terrakok.cicerone.Router
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter(
    private val router: Router,
    private val screens: IScreens,
    private val networkStatus: AndroidNetworkStatus,
    private val db: Unit,
) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users(networkStatus))
    }

    fun backClicked() {
        router.exit()
    }
}
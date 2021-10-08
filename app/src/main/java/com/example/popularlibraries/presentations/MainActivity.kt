package com.example.popularlibraries.presentations

import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.popularlibraries.*
import com.example.popularlibraries.data.GitHubDatabase
import com.example.popularlibraries.databinding.ActivityMainBinding
import com.example.popularlibraries.presentations.abs.AbsActivity
import com.example.popularlibraries.presenters.MainPresenter
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import dagger.Provides
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Singleton

class MainActivity : AbsActivity(R.layout.activity_main), MainView {

    private val navigator = AppNavigator(this, R.id.container)

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var screens: AndroidScreens

    @Inject
    lateinit var networkStatus: AndroidNetworkStatus

    private val presenter by moxyPresenter {
        MainPresenter(
            router,
            screens,
            networkStatus,
            GitHubDatabase.create(this)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidNetworkStatus(this)
    }

    private val viewBinding: ActivityMainBinding by viewBinding(R.id.container)

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if(it is BackButtonListener && it.backPressed()){
                return
            }
        }
        presenter.backClicked()
    }
}
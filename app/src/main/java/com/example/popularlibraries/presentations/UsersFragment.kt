package com.example.popularlibraries.presentations

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.popularlibraries.*
import com.example.popularlibraries.data.*
import com.example.popularlibraries.databinding.FragmentUsersBinding
import com.example.popularlibraries.presentations.abs.AbsFragment
import com.example.popularlibraries.presenters.UsersPresenter
import com.example.popularlibraries.presenters.UsersRVAdapter
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Named

class UsersFragment
    @Inject constructor (
        private val networkStatus: AndroidNetworkStatus
    ) : AbsFragment(R.layout.fragment_users),
    UsersView, BackButtonListener {

    companion object {
        fun newInstance(
            networkStatus: AndroidNetworkStatus): Fragment = UsersFragment(networkStatus)
    }

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: AndroidScreens

    @Inject
    lateinit var mainThread: Scheduler

    private val usersCache: RoomUserCache = RoomUserCache.getInstance()
    private val userReposList = RoomRepositoriesCache.getInstance()

    private val githubRepository = RetrofitGithubUsersRepo(ApiHolder.api, networkStatus, GitHubDatabase.getInstance(), usersCache)

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(
            mainThread,
            githubRepository,
            RetrofitGithubUserReposList(ApiHolder.api, networkStatus, GitHubDatabase.getInstance(),userReposList),
            router,
            screens
        )
    }

    private var adapter: UsersRVAdapter? = null

    private val viewBinding: FragmentUsersBinding by viewBinding()

    override fun init() {
        viewBinding.rvUsers.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter, GlideImageLoader())
        viewBinding.rvUsers.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}
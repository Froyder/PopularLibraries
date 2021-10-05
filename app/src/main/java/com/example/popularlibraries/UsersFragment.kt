package com.example.popularlibraries

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.popularlibraries.databinding.FragmentUsersBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment (private val networkStatus: AndroidNetworkStatus) : MvpAppCompatFragment(R.layout.fragment_users), UsersView, BackButtonListener {

    companion object {
        fun newInstance(networkStatus: AndroidNetworkStatus): Fragment = UsersFragment(networkStatus)
    }

    private val usersCache: RoomUserCache = RoomUserCache.getInstance()
    private val userReposList = RoomRepositoriesCache.getInstance()

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(
            networkStatus,
            AndroidSchedulers.mainThread(),
            RetrofitGithubUsersRepo(ApiHolder.api, networkStatus, Database.getInstance(),usersCache),
            RetrofitGithubUserReposList(ApiHolder.api, networkStatus, Database.getInstance(),userReposList),
            CiceroneObject.router,
            AndroidScreens()
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
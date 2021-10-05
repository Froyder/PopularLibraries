package com.example.popularlibraries

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.popularlibraries.databinding.FragmentDetailsBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_details.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserDetailsFragment(
    private val networkStatus : AndroidNetworkStatus,
    private val user: GithubUser,
    private val db: Database
    ) :
    MvpAppCompatFragment(R.layout.fragment_details), BackButtonListener, UserDetailsView {

    companion object {
        fun newInstance(
            networkStatus: AndroidNetworkStatus,
            user: GithubUser,
            db: Database
        ): Fragment = UserDetailsFragment(networkStatus, user, db)
    }

    private val userReposList = RoomRepositoriesCache.getInstance()

    private val presenter by moxyPresenter {
        UserDetailsPresenter(
            networkStatus,
            RetrofitGithubUserReposList(ApiHolder.api, networkStatus, db, userReposList),
            CiceroneObject.router,
            user,
            AndroidSchedulers.mainThread(),
            AndroidScreens()
        )
    }

    private val viewBinding: FragmentDetailsBinding by viewBinding()

    private var adapter: RepoRVAdapter? = null

    override fun backPressed() = presenter.backPressed()

    override fun setUserPage(userData: GithubUser) {
        viewBinding.detailsName.text = userData.login
        userData.avatarUrl?.let { GlideImageLoader().loadInto(it, details_userImage) }

        viewBinding.rvRepos.layoutManager = LinearLayoutManager(context)
        adapter = RepoRVAdapter(presenter.userRepoListPresenter)
        viewBinding.rvRepos.adapter = adapter
    }

    override fun updateRepoList() {
        adapter?.notifyDataSetChanged()
    }

    override fun onLoadingRepoListError(throwable: Throwable) {
        Toast.makeText(
            context, "Error occurred while loading repo list: $throwable", Toast.LENGTH_SHORT
        ).show()
    }
}
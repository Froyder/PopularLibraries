package com.example.popularlibraries.presentations

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.popularlibraries.*
import com.example.popularlibraries.data.*
import com.example.popularlibraries.databinding.FragmentDetailsBinding
import com.example.popularlibraries.presentations.abs.AbsFragment
import com.example.popularlibraries.presenters.RepoRVAdapter
import com.example.popularlibraries.presenters.UserDetailsPresenter
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import kotlinx.android.synthetic.main.fragment_details.*
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import android.text.style.TtsSpan.ARG_NUMBER

import android.text.style.TtsSpan.ARG_TEXT

import android.os.Bundle
import android.text.style.TtsSpan


class UserDetailsFragment(
    private val user: GithubUser,
    private val db: GitHubDatabase
    ) :
    AbsFragment(R.layout.fragment_details), BackButtonListener, UserDetailsView {

    companion object {
        private const val ARG_USER = "user"

        fun newInstance(user: GithubUser, db: GitHubDatabase, text : String): Fragment {
            val fragment = UserDetailsFragment(user, db)
            fragment.arguments?.putString("login", text)
            return fragment
        }

//        = UserDetailsFragment(user, db).arguments("test" to text)
    }

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var networkStatus : AndroidNetworkStatus

    @Inject
    lateinit var mainThread: Scheduler

    @Inject
    lateinit var screens: AndroidScreens

    private val userReposList = RoomRepositoriesCache.getInstance()

    private val presenter by moxyPresenter {
        UserDetailsPresenter(
            networkStatus,
            RetrofitGithubUserReposList(ApiHolder.api, networkStatus, db, userReposList),
            router,
            user,
            mainThread,
            screens
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
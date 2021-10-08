package com.example.popularlibraries.presenters

import com.example.popularlibraries.AndroidNetworkStatus
import com.example.popularlibraries.IScreens
import com.example.popularlibraries.data.GithubUser
import com.example.popularlibraries.data.IGithubUserReposList
import com.example.popularlibraries.data.UserRepo
import com.example.popularlibraries.presentations.RepoItemView
import com.example.popularlibraries.presentations.UserDetailsView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

class UserDetailsPresenter(
    private val networkStatus : AndroidNetworkStatus,
    private val usersRepo: IGithubUserReposList,
    private val router: Router,
    private val user: GithubUser,
    private val mainThread: Scheduler,
    private val screens: IScreens
) : MvpPresenter<UserDetailsView>() {

    class ReposListPresenter : IRepoListPresenter {
        val userRepoList = mutableListOf<UserRepo>()
        override var itemClickListener: ((RepoItemView) -> Unit)? = null

        override fun bindView(view: RepoItemView) {
            val repo = userRepoList[view.pos]
            repo.name?.let { view.setTitle(it) }
        }

        override fun getCount() = userRepoList.size
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        user.login?.let { viewState.setUserPage(user) }
        getUserRepoList()

        userRepoListPresenter.itemClickListener = { itemView ->
            val repo = userRepoListPresenter.userRepoList[itemView.pos]
            router.navigateTo(screens.repoDetails(user, repo))
        }
    }

    private val disposableUserRepoList = CompositeDisposable()

    val userRepoListPresenter = ReposListPresenter()

    private fun getUserRepoList() {
        disposableUserRepoList.add(
            usersRepo.getUserRepoList(user)
                .observeOn(mainThread)
                .doOnError { println("Error: ${it.message}") }
                .subscribe(
                    { setReposList(it) },
                    { onReturnError (it) }
                )
        )
    }

    private fun setReposList(repos: List<UserRepo>) {
        userRepoListPresenter.userRepoList.clear()
        userRepoListPresenter.userRepoList.addAll(repos)
        viewState.updateRepoList()
    }

    private fun onReturnError(throwable: Throwable) {
        viewState.onLoadingRepoListError(throwable)
    }

    fun backPressed(): Boolean {
        router.navigateTo(screens.users(networkStatus))
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        disposableUserRepoList.dispose()
    }
}
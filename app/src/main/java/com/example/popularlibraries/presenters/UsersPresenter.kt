package com.example.popularlibraries.presenters

import com.example.popularlibraries.AndroidNetworkStatus
import com.example.popularlibraries.IScreens
import com.example.popularlibraries.data.GitHubDatabase
import com.example.popularlibraries.data.GithubUser
import com.example.popularlibraries.data.IGithubUserReposList
import com.example.popularlibraries.data.IGithubUsersRepo
import com.example.popularlibraries.presentations.UserItemView
import com.example.popularlibraries.presentations.UsersView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter

class UsersPresenter(
    private val uiScheduler: Scheduler,
    private val usersRepo: IGithubUsersRepo,
    private val usersRepoList: IGithubUserReposList,
    private val router: Router,
    private val screens: IScreens
)
    : MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            user.login?.let { view.setLogin(it) }
            user.avatarUrl?.let {view.loadAvatar(it)}
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        getUsersFromNet()

        usersListPresenter.itemClickListener = { itemView ->
            val user = usersListPresenter.users[itemView.pos]
            getUserRepoList(user)
            router.navigateTo(screens.userDetails(user, GitHubDatabase.getInstance()))
        }
    }

    private val disposableUsersList = CompositeDisposable()

    private fun getUsersFromNet () {
        disposableUsersList.add(
            usersRepo.getUsers()
                .observeOn(uiScheduler)
                .doOnError { println("Error: ${it.message}") }
                .subscribe { users ->
                    onUsersListComplete(users)
                }
        )
    }

    private fun onUsersListComplete(users: List<GithubUser>) {
        usersListPresenter.users.clear()
        usersListPresenter.users.addAll(users)
        for (user in users) { getUserRepoList(user)}
        viewState.updateList()
    }

    private fun getUserRepoList(user: GithubUser) {
        disposableUsersList.add(
            usersRepoList.getUserRepoList(user)
                .observeOn(uiScheduler)
                .doOnError { println("Error: ${it.message}") }
                .subscribe()
        )
    }

    fun backPressed(): Boolean {
        router.finishChain()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        disposableUsersList.dispose()
    }

}

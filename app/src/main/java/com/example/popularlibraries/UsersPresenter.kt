package com.example.popularlibraries

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import java.util.*

class UsersPresenter(val usersRepo: GithubUsersRepo, val router: Router) : MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        getUsersFromObservable()

        usersListPresenter.itemClickListener = { itemView ->
            //TODO: переход на экран пользователя c помощью router.navigateTo
            val user = usersListPresenter.users[itemView.pos]
            router.navigateTo(AndroidScreens().details(user.id))
        }
    }

    private val disposableUsersList = CompositeDisposable()

    private fun getUsersFromObservable () {
        disposableUsersList.add(
            usersRepo
                .getUsersListObservable()
                .subscribe({ user ->
                    usersListPresenter.users.add(user)
                    viewState.updateList()
                }, { error ->
                    println("Error: ${error.message}")
                })
        )
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}

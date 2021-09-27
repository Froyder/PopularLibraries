package com.example.popularlibraries

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.MaybeOnSubscribe
import io.reactivex.rxjava3.core.Single

object GithubUsersRepo {

    private val repositories = listOf(
        GithubUser(1, "login1"),
        GithubUser(2, "login2"),
        GithubUser(3, "login3"),
        GithubUser(4, "login4"),
        GithubUser(5, "login5")
    )

    fun getUsers(): List<GithubUser> {
        return repositories
    }

    fun getUsersListObservable(): Single<List<GithubUser>> {
        return Single.just(repositories)
    }

    fun getUserDataObservable(userID: Long): Maybe<GithubUser> {
        return Maybe.create(MaybeOnSubscribe {
            if (it.isDisposed) return@MaybeOnSubscribe

            val user = repositories.first { u -> u.id == userID }

            if (!user.equals("")) {
                it.onSuccess((user))
            } else it.onComplete ()
        })
    }

}
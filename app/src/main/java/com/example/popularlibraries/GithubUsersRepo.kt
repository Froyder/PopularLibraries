package com.example.popularlibraries

import io.reactivex.rxjava3.core.Observable

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

    fun getUsersListObservable(): Observable<GithubUser> {
        return Observable.fromIterable(repositories)
    }

    fun getUserDataObservable(userID: Long): Observable<GithubUser> {
        val user = repositories.first { u -> u.id == userID }
        return Observable.just(user)
    }

}
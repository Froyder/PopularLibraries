package com.example.popularlibraries

import io.reactivex.rxjava3.core.Single

interface IGithubUsersRepo {
    fun getUsers(): Single<List<GithubUser>>
    fun getUserRepoList(login: String): Single<List<UserRepo>>
}
package com.example.popularlibraries.data

import io.reactivex.rxjava3.core.Single

interface IGithubUserReposList {
    fun getUserRepoList(user: GithubUser): Single<List<UserRepo>>
}
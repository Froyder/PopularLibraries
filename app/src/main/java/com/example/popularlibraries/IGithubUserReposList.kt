package com.example.popularlibraries

import io.reactivex.rxjava3.core.Single

interface IGithubUserReposList {
    fun getUserRepoList(user: GithubUser): Single<List<UserRepo>>
    fun getCachedData(user: GithubUser):Single<List<UserRepo>>
}
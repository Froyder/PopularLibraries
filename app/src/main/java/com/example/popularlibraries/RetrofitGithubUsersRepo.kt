package com.example.popularlibraries

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(val api: IDataSource) : IGithubUsersRepo {
    override fun getUsers() = api.getUsers().subscribeOn(Schedulers.io())
    override fun getUserRepoList(login: String): Single<List<UserRepo>> =
        api.getUserRepoList(login).subscribeOn(Schedulers.io())
}
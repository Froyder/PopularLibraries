package com.example.popularlibraries

import io.reactivex.rxjava3.core.Single

interface IRepositoriesCache {
    fun setCachedData(roomRepos : List<RoomGithubRepository>)
    fun getCachedData(user: GithubUser): Single<List<UserRepo>>
}
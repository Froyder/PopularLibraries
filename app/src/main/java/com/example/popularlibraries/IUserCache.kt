package com.example.popularlibraries

import io.reactivex.rxjava3.core.Single

interface IUsersCache {
    fun setCachedData(roomRepos : List<RoomGithubUser>)
    fun getCachedData(): Single<List<GithubUser>>
}
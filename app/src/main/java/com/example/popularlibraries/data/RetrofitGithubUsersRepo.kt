package com.example.popularlibraries.data

import com.example.popularlibraries.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo (
    private val api: IDataSource,
    val networkStatus: INetworkStatus,
    val db: GitHubDatabase,
    private val usersCache: IUsersCache
    ) : IGithubUsersRepo {

    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getUsers()
                .flatMap({ users ->
                    Single.fromCallable {
                        val roomUsers = users.map { user ->
                            RoomGithubUser(
                                user.id ?: "",
                                user.login ?: "",
                                user.avatarUrl ?: "",
                                user.repoListUrl ?: ""
                            )
                        }
                        usersCache.setCachedData(roomUsers)
                        users
                    }
                }, { error ->
                    usersCache.getCachedData()
                })
        } else {
            usersCache.getCachedData()
        }
    }.subscribeOn(Schedulers.io())
}
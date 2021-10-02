package com.example.popularlibraries

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    val db: Database
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
                        db.userDao.insert(roomUsers)
                        users
                    }
                }, { error ->
                    getCachedData()
                })
        } else {
            getCachedData()
        }
    }.subscribeOn(Schedulers.io())

    override fun getCachedData(): Single<List<GithubUser>> {
        return Single.fromCallable {
            db.userDao.getAll().map { roomUser ->
                GithubUser(roomUser.id, roomUser.login, roomUser.avatarUrl, roomUser.reposUrl)
            }
        }
    }
}
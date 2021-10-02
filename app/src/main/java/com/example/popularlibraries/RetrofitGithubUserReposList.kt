package com.example.popularlibraries

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUserReposList (
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    val db: Database
    ): IGithubUserReposList {
    override fun getUserRepoList(user: GithubUser) = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
                user.login?.let {
                    api.getUserRepoList(it)
                        .flatMap({ repositories ->
                            Single.fromCallable {
                                val roomUser = user.login.let { db.userDao.findByLogin(it) }
                                    ?: throw RuntimeException("No such user in cache")
                                val roomRepos = repositories.map {
                                    RoomGithubRepository(
                                        (it.id ?: 0),
                                        it.name ?: "",
                                        it.language ?: "",
                                        it.forks ?: 0,
                                        roomUser.id,
                                        it.createdAt ?: ""
                                    )
                                }
                                db.repositoryDao.insert(roomRepos)
                                repositories
                            }
                        }, {error->
                            getCachedData(user)
                        })
                } ?: Single.error<List<UserRepo>>(RuntimeException("User has no repos url")).subscribeOn(Schedulers.io())
        } else {
            getCachedData(user)
        }
    }.subscribeOn(Schedulers.io())

    override fun getCachedData(user: GithubUser): Single<List<UserRepo>> {
        return Single.fromCallable {
            val roomUser = user.login?.let { db.userDao.findByLogin(it) } ?: throw RuntimeException("No such user in cache")
            db.repositoryDao.findForUser(roomUser.id)
                .map {
                    UserRepo(it.id, it.name, it.language, it.forksCount, it.createdAt)
                }
        }
    }

}
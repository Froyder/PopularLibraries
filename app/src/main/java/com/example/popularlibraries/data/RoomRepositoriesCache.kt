package com.example.popularlibraries.data

import io.reactivex.rxjava3.core.Single

class RoomRepositoriesCache: IRepositoriesCache {

    companion object {
        fun getInstance () = RoomRepositoriesCache()
    }

    private val db = GitHubDatabase.getInstance()

    override fun setCachedData(roomRepos: List<RoomGithubRepository>) {
        db.repositoryDao.insert(roomRepos)
    }

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
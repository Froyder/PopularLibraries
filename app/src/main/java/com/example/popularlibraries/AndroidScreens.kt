package com.example.popularlibraries

import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users(networkStatus: AndroidNetworkStatus) = FragmentScreen { UsersFragment.newInstance(networkStatus) }
    override fun userDetails(networkStatus: AndroidNetworkStatus, user: GithubUser, db: Database)
                                = FragmentScreen { UserDetailsFragment.newInstance(networkStatus, user, db) }
    override fun repoDetails(networkStatus: AndroidNetworkStatus, user: GithubUser, repo: UserRepo)
                                = FragmentScreen { RepoDetailsFragment.newInstance(networkStatus, user, repo) }
}
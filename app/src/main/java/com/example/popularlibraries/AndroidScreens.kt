package com.example.popularlibraries

import com.example.popularlibraries.data.GitHubDatabase
import com.example.popularlibraries.data.GithubUser
import com.example.popularlibraries.data.UserRepo
import com.example.popularlibraries.presentations.RepoDetailsFragment
import com.example.popularlibraries.presentations.UserDetailsFragment
import com.example.popularlibraries.presentations.UsersFragment
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users(networkStatus: AndroidNetworkStatus) = FragmentScreen { UsersFragment.newInstance(networkStatus) }
    override fun userDetails(user: GithubUser, db: GitHubDatabase)
                                = FragmentScreen { UserDetailsFragment.newInstance(user, db, "") }
    override fun repoDetails( user: GithubUser, repo: UserRepo)
                                = FragmentScreen { RepoDetailsFragment.newInstance(user, repo) }
}
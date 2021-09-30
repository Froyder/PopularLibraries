package com.example.popularlibraries

import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun userDetails(user: GithubUser) = FragmentScreen { UserDetailsFragment.newInstance(user) }
    override fun repoDetails(user: GithubUser, repo: UserRepo) = FragmentScreen { RepoDetailsFragment.newInstance(user, repo) }
}
package com.example.popularlibraries

import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun details(user: GithubUser) = FragmentScreen { UserDetailsFragment.newInstance(user) }
}
package com.example.popularlibraries

import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(networkStatus: AndroidNetworkStatus): Screen
    fun userDetails(networkStatus: AndroidNetworkStatus, user: GithubUser, db: Database): Screen
    fun repoDetails (networkStatus: AndroidNetworkStatus, user: GithubUser, repo: UserRepo): Screen
}
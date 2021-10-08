package com.example.popularlibraries

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.popularlibraries.data.GitHubDatabase
import com.example.popularlibraries.data.GithubUser
import com.example.popularlibraries.data.UserRepo
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(networkStatus: AndroidNetworkStatus): Screen
    fun userDetails(user: GithubUser, db: GitHubDatabase): Screen
    fun repoDetails (user: GithubUser, repo: UserRepo): Screen

}
package com.example.popularlibraries

import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun userDetails(user: GithubUser): Screen
    fun repoDetails (user: GithubUser, repo: UserRepo): Screen
}
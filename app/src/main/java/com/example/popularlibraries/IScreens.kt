package com.example.popularlibraries

import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun details(user: GithubUser): Screen
}
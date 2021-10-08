package com.example.popularlibraries.DI

import com.example.popularlibraries.data.*
import com.example.popularlibraries.presentations.MainActivity
import com.example.popularlibraries.presentations.RepoDetailsFragment
import com.example.popularlibraries.presentations.UserDetailsFragment
import com.example.popularlibraries.presentations.UsersFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
interface GitHubUsersModule {

    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    fun bindUsersFragment(): UsersFragment

    @ContributesAndroidInjector
    fun bindUserDetailsFragment(): UserDetailsFragment

    @ContributesAndroidInjector
    fun bindRepoDetailsFragment(): RepoDetailsFragment


//    @Singleton
//    @Binds
//    fun bindGitHubUserRepository(repositories: RetrofitGithubUserReposList): IGithubUserReposList
//
//    @Singleton
//    @Binds
//    fun bindUserDataSource(dataSource: RetrofitGithubUsersRepo): IGithubUsersRepo

//    @Singleton
//    @Binds
//    fun bindCacheUserDataSource(dataSource: RoomUserCache): IUsersCache

}
package com.example.popularlibraries.DI

import android.content.Context
import androidx.room.Room
import com.example.popularlibraries.data.GitHubDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface GitHubStorageModule {

    @Singleton
    fun provideGitHubDatabaseStorage(context: Context): GitHubDatabase =
            Room.databaseBuilder(context, GitHubDatabase::class.java, "github.db")
                .build()
}
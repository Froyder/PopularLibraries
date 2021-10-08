package com.example.popularlibraries.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

@androidx.room.Database(entities = [RoomGithubUser::class, RoomGithubRepository::class], version = 2)
abstract class GitHubDatabase : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao

    companion object {
        private const val DB_NAME = "database.db"
        private var instance: GitHubDatabase? = null
        fun getInstance() = instance ?: throw RuntimeException("Database has not been created. Please call create(context)")

        fun create(context: Context?) {
            if (instance == null) {
                instance = Room.databaseBuilder(context!!, GitHubDatabase::class.java, DB_NAME)
                    .build()
            }
        }
    }
}
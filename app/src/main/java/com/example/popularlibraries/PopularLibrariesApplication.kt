package com.example.popularlibraries

import androidx.room.Database
import com.example.popularlibraries.DI.DaggerPopularLibrariesComponent
import com.example.popularlibraries.DI.PopularLibrariesComponent
import com.example.popularlibraries.data.GitHubDatabase
import com.github.terrakok.cicerone.Cicerone
import dagger.Provides
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import javax.inject.Singleton

class PopularLibrariesApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<PopularLibrariesApplication> =
        DaggerPopularLibrariesComponent
            .builder()
            .withContext(applicationContext)
            .apply {
                val cicerone = Cicerone.create()
                withRouter(cicerone.router)
                withNavigatorHolder(cicerone.getNavigatorHolder())
            }
            .withMainThreadScheduler(AndroidSchedulers.mainThread())
            .withAndroidScreens(AndroidScreens())
            .withAndroidNetworkStatus(AndroidNetworkStatus(applicationContext))
            .build()

    override fun onCreate() {
        super.onCreate()
        RxJavaPlugins.setErrorHandler {}
    }
}
package com.example.popularlibraries.DI

import android.content.Context
import com.example.popularlibraries.AndroidNetworkStatus
import com.example.popularlibraries.AndroidScreens
import com.example.popularlibraries.PopularLibrariesApplication
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Singleton

@Singleton
@Component (modules = [
    AndroidInjectionModule::class,
    GitHubUsersModule::class,
    GitHubStorageModule::class]
) interface PopularLibrariesComponent : AndroidInjector <PopularLibrariesApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun withContext(context: Context): Builder

        @BindsInstance
        fun withRouter(router: Router): Builder

        @BindsInstance
        fun withNavigatorHolder(navigatorHolder: NavigatorHolder): Builder

        @BindsInstance
        fun withMainThreadScheduler(mainThread: Scheduler): Builder

//        @BindsInstance
//        fun dataBaseModule(database: GitHubDatabase): Builder

        @BindsInstance
        fun withAndroidScreens (screens: AndroidScreens): Builder

        @BindsInstance
        fun withAndroidNetworkStatus (networkStatus: AndroidNetworkStatus): Builder

        fun build (): PopularLibrariesComponent
    }

}
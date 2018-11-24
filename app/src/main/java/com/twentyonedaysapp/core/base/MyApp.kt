package com.twentyonedaysapp.core.base

import android.app.Application
import com.twentyonedaysapp.BuildConfig
import com.twentyonedaysapp.core.di.ApplicationComponent
import com.twentyonedaysapp.core.di.ApplicationModule
import com.twentyonedaysapp.core.di.DaggerApplicationComponent
import timber.log.Timber

class MyApp : Application() {

   val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }


    private fun injectMembers() = appComponent.inject(this)


    override fun onCreate() {
        super.onCreate()
        this.injectMembers()
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }

    }
}
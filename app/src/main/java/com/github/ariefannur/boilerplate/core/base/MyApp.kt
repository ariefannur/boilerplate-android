package com.github.ariefannur.boilerplate.core.base

import android.app.Application
import com.github.ariefannur.boilerplate.core.di.ApplicationComponent
import com.github.ariefannur.boilerplate.core.di.ApplicationModule
import com.github.ariefannur.boilerplate.core.di.DaggerApplicationComponent
import com.github.ariefannur.boilerplate.BuildConfig
import timber.log.Timber

open class MyApp : Application() {

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
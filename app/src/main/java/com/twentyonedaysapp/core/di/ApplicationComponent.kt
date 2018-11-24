package com.twentyonedaysapp.core.di

import com.twentyonedaysapp.core.base.MyApp
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent{

    fun inject(application: MyApp)
}
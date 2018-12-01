package com.github.ariefannur.boilerplate.core.di

import com.github.ariefannur.boilerplate.core.base.MyApp
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent{

    fun inject(application: MyApp)
}
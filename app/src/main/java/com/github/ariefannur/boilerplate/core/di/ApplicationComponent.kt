package com.github.ariefannur.boilerplate.core.di

import com.github.ariefannur.boilerplate.core.base.BaseActivity
import com.github.ariefannur.boilerplate.core.base.BaseFragment
import com.github.ariefannur.boilerplate.core.base.MyApp
import com.github.ariefannur.boilerplate.core.di.viewmodel.ViewModelModul
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModul::class])
interface ApplicationComponent{

    fun inject(application: MyApp)
    fun inject(baseActivity: BaseActivity)
    fun inject(baseFragment: BaseFragment)
}
package com.github.ariefannur.boilerplate.core.di

import android.app.Application
import android.content.Context
import com.github.ariefannur.boilerplate.BuildConfig
import com.github.ariefannur.boilerplate.core.network.Api
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule (private val application: Application){

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application

    @Provides @Singleton fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(OkhttpProvider.createClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    fun geerateApi():Api{
        return provideRetrofit().create(Api::class.java)
    }
}
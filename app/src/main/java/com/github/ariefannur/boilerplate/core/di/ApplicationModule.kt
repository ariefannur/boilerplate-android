package com.github.ariefannur.boilerplate.core.di

import android.app.Application
import android.content.Context
import com.github.ariefannur.boilerplate.BuildConfig
import com.github.ariefannur.boilerplate.core.network.Api
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApplicationModule (private val application: Application){

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application

    @Provides @Singleton fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private fun createClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        okHttpClientBuilder.writeTimeout(30, TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(30, TimeUnit.SECONDS)
        okHttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS)
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun geerateApi():Api{
        return provideRetrofit().create(Api::class.java)
    }
}
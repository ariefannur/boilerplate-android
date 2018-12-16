package com.github.ariefannur.boilerplate.core.network

import com.github.ariefannur.boilerplate.core.model.BaseResult
import com.github.ariefannur.boilerplate.core.model.Contributor
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {
    @POST("login")
    fun login() : BaseResult

    @GET("square/retrofit/contributors")
    fun getContributor(): Observable<List<Contributor>>
}
package com.hanna.pagingmovies.data.core

import com.hanna.pagingmovies.BuildConfig
import javax.inject.Inject
import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val httpUrl = chain.request()
            .url.newBuilder()
            .addQueryParameter("api_key", BuildConfig.API_KEY)
            .build()

        val request = chain.request()
            .newBuilder()
            .url(httpUrl)
            .build()

        return chain.proceed(request)
    }
}
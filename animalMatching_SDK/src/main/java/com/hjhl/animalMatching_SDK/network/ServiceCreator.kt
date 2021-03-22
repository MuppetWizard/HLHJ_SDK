package com.hjhl.animalMatching_SDK.network

import com.hjhl.animalMatching_SDK.base.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceCreator {

    private val httpClient = OkHttpClient.Builder()
    private val retrofit: Retrofit
        get() {
            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
            return retrofit.build()
        }

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    inline fun <reified T> create(): T = create(T::class.java)
}
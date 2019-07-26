package com.thadocizn.brav.services

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


class RetroInstance {

    companion object {

        const val BASE_URL = "https://brav-staging.herokuapp.com/"
    }


    val service: BravApi
        get() {

            var okHttpClient: OkHttpClient = OkHttpClient().newBuilder()

                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .build()

            var retroInstance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

            return retroInstance.create(BravApi::class.java)
        }

}
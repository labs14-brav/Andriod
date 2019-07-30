package com.thadocizn.brav.services

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


class RetroInstance {

    companion object {

        const val BASE_URL = "https://brav-staging.herokuapp.com/"
    }


    fun service(token: String?): BravApi {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            var okHttpClient: OkHttpClient = OkHttpClient().newBuilder()

                .addInterceptor(interceptor)
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .addNetworkInterceptor { chain ->
                    val newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", token.toString())
                        .build()
                    chain.proceed(newRequest) }
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
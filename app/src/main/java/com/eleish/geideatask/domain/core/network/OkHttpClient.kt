package com.eleish.geideatask.domain.core.network

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object OkHttpClient {
    fun newInstance(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            Log.d(
                "OkHttpClient",
                "API Request -> $message"
            )
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val okHttpClientBuilder = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(20, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)

        return okHttpClientBuilder.build()
    }
}
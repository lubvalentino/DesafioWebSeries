package com.example.desafio_web_series.api

import com.example.desafio_web_series.extension.md5
import com.example.desafio_web_series.units.Constants.Api.API_HASH_NAME
import com.example.desafio_web_series.units.Constants.Api.API_KEY_NAME
import com.example.desafio_web_series.units.Constants.Api.API_TS_NAME
import com.example.desafio_web_series.units.Constants.Api.BASE_URL_MARVEL
import com.example.desafio_web_series.units.Constants.Api.MARVEL_KEY
import com.example.desafio_web_series.units.Constants.Api.PUBLIC_KEY
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiService {
    val marvelApi = getMarvelComics().create(MarvelApi::class.java)

    private fun getMarvelComics(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_MARVEL)
            .client(getMarvelInterceptorClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getMarvelInterceptorClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val interceptor = OkHttpClient.Builder()
            .connectTimeout(5,TimeUnit.SECONDS)
            .readTimeout(10,TimeUnit.SECONDS)
            .writeTimeout(10,TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val ts = System.currentTimeMillis()
                val hash = ("$ts$MARVEL_KEY$PUBLIC_KEY").md5()
                val url = chain.request().url().newBuilder()
                    .addQueryParameter(API_TS_NAME, ts.toString())
                    .addQueryParameter(API_KEY_NAME, PUBLIC_KEY)
                    .addQueryParameter(API_HASH_NAME, hash)
                    .build()
                val newRequest = chain.request().newBuilder().url(url).build()
                chain.proceed(newRequest)
            }
        return interceptor.build()
    }
}
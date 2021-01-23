package com.example.desafio_web_series.api

import com.example.desafio_web_series.model.TotalComics
import retrofit2.Response
import retrofit2.http.GET

interface MarvelApi {

    @GET("comics")
    suspend fun comics(): Response<TotalComics>
}
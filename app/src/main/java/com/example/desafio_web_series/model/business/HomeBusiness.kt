package com.example.desafio_web_series.model.business

import com.example.desafio_web_series.api.ResponseApi
import com.example.desafio_web_series.repository.HomeRepository

class HomeBusiness {

    private val repository by lazy {
        HomeRepository()
    }
    suspend fun getComics(): ResponseApi {
        return repository.getComics()
    }
}
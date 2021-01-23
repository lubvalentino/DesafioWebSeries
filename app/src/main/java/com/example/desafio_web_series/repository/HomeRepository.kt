package com.example.desafio_web_series.repository

import com.example.desafio_web_series.api.ApiService
import com.example.desafio_web_series.api.ResponseApi

class HomeRepository {
    suspend fun getComics(): ResponseApi {
        return try {
            val response = ApiService.marvelApi.comics()
            if (response.isSuccessful){
                ResponseApi.Success(response.body())
            } else{
                ResponseApi.Error("Erro ao carregar dados")
            }
        }catch (exception: Exception){
            ResponseApi.Error("Erro ao carregar dados")
        }

    }

}
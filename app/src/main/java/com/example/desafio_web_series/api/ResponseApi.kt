package com.example.desafio_web_series.api

sealed class ResponseApi {
    class Success (val data: Any?): ResponseApi()
    class Error (val message: String): ResponseApi()
}
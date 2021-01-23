package com.example.desafio_web_series.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafio_web_series.api.ResponseApi
import com.example.desafio_web_series.model.business.HomeBusiness
import com.example.desafio_web_series.model.TotalComics
import kotlinx.coroutines.launch

class HomeViewModel:ViewModel() {

    val onResultComics: MutableLiveData<TotalComics> = MutableLiveData()
    private val onResultFailure: MutableLiveData<String> = MutableLiveData()

    private val business by lazy {
        HomeBusiness()
    }
    fun getComics() {
        viewModelScope.launch {
            when (val response = business.getComics()){
                is ResponseApi.Success -> {
                    onResultComics.postValue(
                        response.data as TotalComics
                    )
                }
                is ResponseApi.Error -> {
                    onResultFailure.postValue(response.message)
                }
            }
        }

    }
}
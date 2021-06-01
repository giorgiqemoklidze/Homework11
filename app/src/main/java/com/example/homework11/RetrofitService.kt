package com.example.homework11

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitService {
    private const val Url = "https://restcountries.eu"

    fun RetrofitService() :RetrofitRepository{
        return Retrofit.Builder().baseUrl(Url).addConverterFactory(GsonConverterFactory.create())
                .build().create(RetrofitRepository::class.java)
    }
}
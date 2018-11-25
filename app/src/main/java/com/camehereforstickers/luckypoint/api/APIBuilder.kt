package com.camehereforstickers.luckypoint.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIBuilder {

    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getAPI(): APIInterface{
        return retrofit.create(APIInterface::class.java)
    }
}
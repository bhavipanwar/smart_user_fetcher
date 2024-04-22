package com.bpgurugram.smartdatafetcher.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
class ApiClient {
    private val BASE_URL = "https://jsonplaceholder.typicode.com/"

    @Provides
    fun getApi(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    fun getUserApi() : UserApi
    {
        return ApiClient().getApi().create(UserApi::class.java)
    }

}
package com.shubham.yassirapp.data.di

import com.shubham.yassirapp.data.api.RetrofitService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetModule(private val base_url: String) {

    @Singleton
    @Provides
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(base_url).build()
    }

    @Singleton
    @Provides
    fun provideRetroFitService(retrofit : Retrofit): RetrofitService {
        return retrofit.create(RetrofitService::class.java)
    }


}
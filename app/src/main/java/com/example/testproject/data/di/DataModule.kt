package com.example.testproject.data.di

import com.example.testproject.data.network.ApiService
import com.example.testproject.data.repository.PostRepository
import com.example.testproject.data.repository.UserRepository
import com.example.testproject.data.repositoryImpl.PostImpl
import com.example.testproject.data.repositoryImpl.UserImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
class DataModule {


    @Provides
    fun provideApiService(): ApiService {
        return Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiService::class.java)
    }

    @Provides
    fun providePostImpl(apiService: ApiService):PostRepository{
        return PostImpl(apiService)
    }

    @Provides
    fun provideUserImpl(apiService: ApiService):UserRepository{
        return UserImpl(apiService)
    }

}
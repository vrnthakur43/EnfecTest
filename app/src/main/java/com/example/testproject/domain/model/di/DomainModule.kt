package com.example.testproject.domain.model.di

import com.example.testproject.data.repository.PostRepository
import com.example.testproject.data.repository.UserRepository
import com.example.testproject.domain.model.usecase.UserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    fun providePostUseCase(postRepo: PostRepository,userRepo:UserRepository):UserUseCase{
        return UserUseCase(postRepo,userRepo)
    }
}

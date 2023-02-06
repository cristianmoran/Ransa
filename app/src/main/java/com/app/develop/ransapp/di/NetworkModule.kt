package com.app.develop.ransapp.di

import com.app.develop.ransapp.service.AuthService

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {



    @Provides
    fun provideMovieApi(retrofit: Retrofit): AuthService {
        return retrofit.create(AuthService::class.java)
    }

}
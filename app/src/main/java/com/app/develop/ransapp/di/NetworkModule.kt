package com.app.develop.ransapp.di

import com.app.develop.ransapp.ui.login.service.LoginService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideMovieApi(retrofit: Retrofit): LoginService {
        return retrofit.create(LoginService::class.java)
    }

}
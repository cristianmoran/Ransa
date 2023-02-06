package com.app.develop.ransapp.di

import com.app.develop.ransapp.service.AuthService
import com.app.develop.ransapp.service.EntryService

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideAuthService(@Named("seguridad-api")retrofit: Retrofit): AuthService {
        return retrofit.create(AuthService::class.java)
    }

    @Provides
    fun provideEntryService(@Named("ingreso-api")retrofit: Retrofit): EntryService {
        return retrofit.create(EntryService::class.java)
    }

}
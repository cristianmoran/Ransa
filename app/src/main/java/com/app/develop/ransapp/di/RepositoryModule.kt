package com.app.develop.ransapp.di

import com.app.develop.ransapp.repository.LoginRepository
import com.app.develop.ransapp.repository.impl.LoginRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideLoginRepositoryImpl(dataRepository: LoginRepositoryImpl): LoginRepository



}
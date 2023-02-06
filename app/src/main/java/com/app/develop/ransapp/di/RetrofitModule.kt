package com.app.develop.ransapp.di

import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.preference.PreferenceManager
import com.app.develop.ransapp.BuildConfig.DEBUG
import com.app.develop.ransapp.base.core.CustomCallAdapterFactory
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {


    companion object{
        const val CONNECT_TIMEOUT: Long = 60 * 1000
        const val READ_TIMEOUT: Long = 60 * 1000
        const val WRITE_TIMEOUT: Long = 60 * 1000
    }

    @Provides
    @Singleton
    internal fun provideGson(): GsonConverterFactory {
        return GsonConverterFactory.create(GsonBuilder().create())
    }

    @Provides
    @Singleton
    internal fun providesAppVersion(context: Context): String {
        var appVersion = "Desconocido (>= 1.1.8)"
        try {
            val packageInfo =
                context.packageManager.getPackageInfo(context.packageName, 0)
            appVersion = packageInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        println("NetworkModule providesAppVersion appVersion : $appVersion")
        return appVersion
    }


    @Provides
    @Singleton
    internal fun provideOkHttpClientAgreeement(interceptor: Interceptor, context: Context): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
        okHttpClient.readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
        okHttpClient.writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        /*val socketFactory = context.configSSLConfig(SSLConfigType.AGREEMENT)?.socketFactory
        val trustManager = configTrustManager()
        if (socketFactory != null && trustManager != null)
            okHttpClient.sslSocketFactory(socketFactory, trustManager)*/

        okHttpClient.addInterceptor(interceptor)
            .addInterceptor(httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            })
        if (DEBUG) okHttpClient.addInterceptor(ChuckerInterceptor.Builder(context).build())
        return okHttpClient.build()
    }


    @Provides
    @Singleton
   // @Named("seguridad-api")
    internal fun provideRetrofitSeguridad(
        gsonConverterFactory: GsonConverterFactory,okHttpClient: OkHttpClient, context: Context
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://acd-api-seguridad.herokuapp.com/")
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(CustomCallAdapterFactory(context)) //Set call to return {@link Observable}
            .build()

    }
/*
    @Provides
    @Singleton
    @Named("ingreso-api")
    internal fun provideRetrofitIngreso(
        gsonConverterFactory: GsonConverterFactory,okHttpClient: OkHttpClient, context: Context
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://acd-api-ingreso.herokuapp.com/")
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(CustomCallAdapterFactory(context)) //Set call to return {@link Observable}
            .build()
    }*/

    @Provides
    @Singleton
    internal fun providesInterceptor(
        appVersion: String//, prefs: PreferenceManager
    ): Interceptor {
        return Interceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .header("X-Version-App", appVersion)
                //.header("Authorization", "Bearer ${prefs.getToken()}")
                .method(original.method, original.body)
            val request = requestBuilder.build()
            chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context {
        return application.applicationContext
    }


}

package com.example.melichallenge.di

import com.example.melichallenge.provider.MeliProvider
import com.example.melichallenge.util.NetworkConnectionInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ProviderModule {

    @Provides
    @Named("BaseUrl")
    fun provideBaseUrl() = "https://api.mercadolibre.com/".toHttpUrl()

    @Singleton
    @Provides
    fun provideRetrofit(@Named("BaseUrl") baseUrl: HttpUrl): Retrofit {
        val oktHttpClient = OkHttpClient.Builder()
            .addInterceptor(NetworkConnectionInterceptor())
            .callTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(oktHttpClient.build())
            .build()
    }

    @Provides
    @Singleton
    fun providerMeliProvider(retrofit: Retrofit): MeliProvider =
        retrofit.create(MeliProvider::class.java)
}
package com.example.melichallenge.di

import com.example.melichallenge.provider.MeliProvider
import com.example.melichallenge.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providerCategoriesMainRepository(provider: MeliProvider): CategoriesMainRepository =
        CategoriesMainRepositoryImp(provider)

    @Provides
    @Singleton
    fun providerSearchItemsRepository(provider: MeliProvider): SearchItemsRepository =
        SearchItemsRepositoryImp(provider)

    @Provides
    @Singleton
    fun providerItemsDetailRepository(provider: MeliProvider): ItemsDetailRepository =
        ItemsDetailRepositoryImp(provider)

}
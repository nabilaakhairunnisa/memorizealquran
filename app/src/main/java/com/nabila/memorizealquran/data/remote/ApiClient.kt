package com.nabila.memorizealquran.data.remote

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiClient {
    @Provides
    @Singleton
    fun provideQuranApiService(): ApiService {
        return retrofit2.Retrofit.Builder()
            .baseUrl("https://equran.id/")
            .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
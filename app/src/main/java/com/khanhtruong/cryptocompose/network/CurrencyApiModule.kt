package com.khanhtruong.cryptocompose.network

import com.khanhtruong.cryptocompose.network.api.CurrencyAPI
import com.khanhtruong.cryptocompose.network.api.CurrencyService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object CurrencyApiModule {
    @Provides
    fun provideCurrencyApi(): CurrencyAPI {
        return CurrencyService.create()
    }
}

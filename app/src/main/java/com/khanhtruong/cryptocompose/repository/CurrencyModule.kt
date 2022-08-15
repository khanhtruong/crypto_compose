package com.khanhtruong.cryptocompose.repository

import com.khanhtruong.cryptocompose.database.DatabaseRepo
import com.khanhtruong.cryptocompose.network.api.CurrencyAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object CurrencyModule {
    @Provides
    fun provideCurrencyRepo(currencyApi: CurrencyAPI, databaseRepo: DatabaseRepo): CurrencyRepo {
        return CurrencyRepo(currencyApi, databaseRepo)
    }
}

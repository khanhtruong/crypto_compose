package com.khanhtruong.cryptocompose.repository

import android.content.Context
import com.khanhtruong.cryptocompose.database.DatabaseRepo
import com.khanhtruong.cryptocompose.network.api.CurrencyAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope

@InstallIn(SingletonComponent::class)
@Module
object CurrencyModule {
    @Provides
    fun provideCurrencyRepo(
        currencyApi: CurrencyAPI,
        databaseRepo: DatabaseRepo,
    ): CurrencyRepo {
        return CurrencyRepo(currencyApi, databaseRepo)
    }
}

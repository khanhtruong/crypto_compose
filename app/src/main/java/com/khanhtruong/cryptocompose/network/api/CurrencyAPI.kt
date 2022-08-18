package com.khanhtruong.cryptocompose.network.api

import com.khanhtruong.cryptocompose.model.Currency
import com.khanhtruong.cryptocompose.network.config.APIProvider
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyAPI {
    @GET("/api/v3/coins/markets")
    suspend fun getCurrencies(
        @Query("vs_currency") byCurrency: String
    ): List<Currency>
}

class CurrencyService private constructor() {
    companion object {
        fun create(): CurrencyAPI {
            val retrofit = APIProvider.getRetrofitBuilder().build()
            return retrofit.create(CurrencyAPI::class.java)
        }
    }
}
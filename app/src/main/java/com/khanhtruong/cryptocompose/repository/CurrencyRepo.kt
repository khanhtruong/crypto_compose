package com.khanhtruong.cryptocompose.repository

import com.khanhtruong.cryptocompose.model.Currency
import com.khanhtruong.cryptocompose.database.DatabaseRepo
import com.khanhtruong.cryptocompose.network.api.CurrencyAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class CurrencyRepo(private val currencyApi: CurrencyAPI, private val databaseRepo: DatabaseRepo) {
    fun getCurrencies(byCurrency: String): Flow<List<Currency>> {
        return merge(
            databaseRepo.getCurrency().map { currency ->
                currency?.map { it.toCurrency() } ?: listOf()
            },
            flow {
                val list = currencyApi.getCurrencies(byCurrency)

                if (list.isNotEmpty()) {
                    databaseRepo.bulkInsert(list.map { it.toCurrency() })
                    emit(list)
                }
            }
        ).flowOn(Dispatchers.IO)
    }
}
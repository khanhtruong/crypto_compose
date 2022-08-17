package com.khanhtruong.cryptocompose.repository

import com.khanhtruong.cryptocompose.database.DatabaseRepo
import com.khanhtruong.cryptocompose.model.Currency
import com.khanhtruong.cryptocompose.network.api.CurrencyAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class CurrencyRepo(
    private val currencyApi: CurrencyAPI,
    private val databaseRepo: DatabaseRepo,
) {
    private fun getRemoteCurrency(byCurrency: String): Flow<List<Currency>> {
        return flow<List<Currency>> {
            val currencies = currencyApi.getCurrencies(byCurrency)
            emit(currencies)
        }.onEach {
            databaseRepo.bulkInsert(it.map { it2 -> it2.toCurrency() })
        }.flowOn(Dispatchers.IO)
    }

    private fun getLocalCurrency(): Flow<List<Currency>> {
        return databaseRepo.getCurrency().map { it?.map { it2 -> it2.toCurrency() } ?: listOf() }
    }

    fun getCurrencies(refresh: Boolean = false, byCurrency: String): Flow<List<Currency>> {
        return if (refresh) {
            getRemoteCurrency(byCurrency)
        } else {
            getLocalCurrency()
        }
    }
}
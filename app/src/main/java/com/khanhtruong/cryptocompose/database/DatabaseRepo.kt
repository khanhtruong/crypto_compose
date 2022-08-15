package com.khanhtruong.cryptocompose.database

import com.khanhtruong.cryptocompose.database.entity.Currency
import kotlinx.coroutines.flow.Flow

class DatabaseRepo(private val dbService: DatabaseService) {
    fun getCurrency(): Flow<List<Currency>?> {
        return dbService.currencyDao().get()
    }

    fun bulkInsert(currencies: List<Currency>): List<Long> {
        return dbService.currencyDao().bulkInsert(currencies)
    }
}
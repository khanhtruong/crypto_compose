package com.khanhtruong.cryptocompose.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.khanhtruong.cryptocompose.database.dao.CurrencyDao
import com.khanhtruong.cryptocompose.database.entity.Currency

@Database(entities = [Currency::class], version = 1, exportSchema = false)
abstract class DatabaseService : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao
}
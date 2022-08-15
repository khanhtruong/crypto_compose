package com.khanhtruong.cryptocompose.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.khanhtruong.cryptocompose.database.entity.Currency
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {
    @Query("SELECT * FROM currency")
    fun get(): Flow<List<Currency>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(currency: Currency): Long

    fun bulkInsert(list: List<Currency>): List<Long> {
        val result = mutableListOf<Long>()

        list.forEach { currency ->
            insert(currency)
        }

        return result
    }
}
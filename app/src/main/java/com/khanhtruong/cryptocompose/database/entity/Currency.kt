package com.khanhtruong.cryptocompose.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.khanhtruong.cryptocompose.model.Currency

@Entity
data class Currency(
    @PrimaryKey var id: String = "",
    @ColumnInfo(name = "symbol") var symbol: String? = null,
    @ColumnInfo(name = "name") var name: String? = null,
    @ColumnInfo(name = "image") var image: String? = null,
    @ColumnInfo(name = "current_price") var currentPrice: Double? = null,
    @ColumnInfo(name = "market_cap") var marketCap: Double? = null,
    @ColumnInfo(name = "market_cap_rank") var marketCapRank: Double? = null,
    @ColumnInfo(name = "fully_diluted_valuation") var fullyDilutedValuation: Double? = null,
    @ColumnInfo(name = "total_volume") var totalVolume: Double? = null,
    @ColumnInfo(name = "high_24h") var high24h: Double? = null,
    @ColumnInfo(name = "low_24h") var low24h: Double? = null,
    @ColumnInfo(name = "price_change_24h") var priceChange24h: Double? = null,
    @ColumnInfo(name = "price_change_percentage_24h") var priceChangePercentage24h: Double? = null,
    @ColumnInfo(name = "market_cap_change_24h") var marketCapChange24h: Double? = null,
    @ColumnInfo(name = "market_cap_change_percentage_24h") var marketCapChangePercentage24h: Double? = null,
    @ColumnInfo(name = "circulating_supply") var circulatingSupply: Double? = null,
    @ColumnInfo(name = "total_supply") var totalSupply: Double? = null,
    @ColumnInfo(name = "max_supply") var maxSupply: Double? = null,
    @ColumnInfo(name = "ath") var ath: Double? = null,
    @ColumnInfo(name = "ath_change_percentage") var athChangePercentage: Double? = null,
    @ColumnInfo(name = "ath_date") var athDate: String? = null,
    @ColumnInfo(name = "atl") var atl: Double? = null,
    @ColumnInfo(name = "atl_change_percentage") var atlChangePercentage: Double? = null,
    @ColumnInfo(name = "atl_date") var atlDate: String? = null,
    @ColumnInfo(name = "last_updated") var lastUpdated: String? = null
) {
    fun toCurrency(): Currency {
        return Currency(
            this.id,
            this.symbol,
            this.name,
            this.image,
            this.currentPrice,
            this.marketCap,
            this.marketCapRank,
            this.fullyDilutedValuation,
            this.totalVolume,
            this.high24h,
            this.low24h,
            this.priceChange24h,
            this.priceChangePercentage24h,
            this.marketCapChange24h,
            this.marketCapChangePercentage24h,
            this.circulatingSupply,
            this.totalSupply,
            this.maxSupply,
            this.ath,
            this.athChangePercentage,
            this.athDate,
            this.atl,
            this.atlChangePercentage,
            this.atlDate,
            this.lastUpdated,
        )
    }
}

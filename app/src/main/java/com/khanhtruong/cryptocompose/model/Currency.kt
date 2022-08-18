package com.khanhtruong.cryptocompose.model

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

data class Currency(
    @field:Json(name = "id") var id: String? = null,
    @field:Json(name = "symbol") var symbol: String? = null,
    @field:Json(name = "name") var name: String? = null,
    @field:Json(name = "image") var image: String? = null,
    @field:Json(name = "current_price") var currentPrice: Double? = null,
    @field:Json(name = "market_cap") var marketCap: Double? = null,
    @field:Json(name = "market_cap_rank") var marketCapRank: Double? = null,
    @field:Json(name = "fully_diluted_valuation") var fullyDilutedValuation: Double? = null,
    @field:Json(name = "total_volume") var totalVolume: Double? = null,
    @field:Json(name = "high_24h") var high24h: Double? = null,
    @field:Json(name = "low_24h") var low24h: Double? = null,
    @field:Json(name = "price_change_24h") var priceChange24h: Double? = null,
    @field:Json(name = "price_change_percentage_24h") var priceChangePercentage24h: Double? = null,
    @field:Json(name = "market_cap_change_24h") var marketCapChange24h: Double? = null,
    @field:Json(name = "market_cap_change_percentage_24h") var marketCapChangePercentage24h: Double? = null,
    @field:Json(name = "circulating_supply") var circulatingSupply: Double? = null,
    @field:Json(name = "total_supply") var totalSupply: Double? = null,
    @field:Json(name = "max_supply") var maxSupply: Double? = null,
    @field:Json(name = "ath") var ath: Double? = null,
    @field:Json(name = "ath_change_percentage") var athChangePercentage: Double? = null,
    @field:Json(name = "ath_date") var athDate: String? = null,
    @field:Json(name = "atl") var atl: Double? = null,
    @field:Json(name = "atl_change_percentage") var atlChangePercentage: Double? = null,
    @field:Json(name = "atl_date") var atlDate: String? = null,
    @field:Json(name = "last_updated") var lastUpdated: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(symbol)
        parcel.writeString(name)
        parcel.writeString(image)
        parcel.writeValue(currentPrice)
        parcel.writeValue(marketCap)
        parcel.writeValue(marketCapRank)
        parcel.writeValue(fullyDilutedValuation)
        parcel.writeValue(totalVolume)
        parcel.writeValue(high24h)
        parcel.writeValue(low24h)
        parcel.writeValue(priceChange24h)
        parcel.writeValue(priceChangePercentage24h)
        parcel.writeValue(marketCapChange24h)
        parcel.writeValue(marketCapChangePercentage24h)
        parcel.writeValue(circulatingSupply)
        parcel.writeValue(totalSupply)
        parcel.writeValue(maxSupply)
        parcel.writeValue(ath)
        parcel.writeValue(athChangePercentage)
        parcel.writeString(athDate)
        parcel.writeValue(atl)
        parcel.writeValue(atlChangePercentage)
        parcel.writeString(atlDate)
        parcel.writeString(lastUpdated)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Currency> {
        override fun createFromParcel(parcel: Parcel): Currency {
            return Currency(parcel)
        }

        override fun newArray(size: Int): Array<Currency?> {
            return arrayOfNulls(size)
        }
    }

    fun toCurrency(): com.khanhtruong.cryptocompose.database.entity.Currency {
        return com.khanhtruong.cryptocompose.database.entity.Currency(
            this.id ?: "unknow",
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

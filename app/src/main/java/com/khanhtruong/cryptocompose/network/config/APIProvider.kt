package com.khanhtruong.cryptocompose.network.config

import com.khanhtruong.cryptocompose.util.Constants
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

object APIProvider {

    private fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    this.level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Accept", "application/json")
                    .build()

                chain.proceed(request)
            }
            .build()
    }

    fun getRetrofitBuilder(): Retrofit.Builder {
        val moshi = Moshi.Builder()
            .add(Date::class.java, DateJsonAdapter())
            .build()

        return Retrofit.Builder()
            .client(getClient())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(Constants.SERVER_ENDPOINT + "/")
    }

    class DateJsonAdapter : JsonAdapter<Date>() {

        private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

        override fun fromJson(reader: JsonReader): Date? {
            return try {
                val dateAsString = reader.nextString()
                dateFormat.parse(dateAsString)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }

        override fun toJson(writer: JsonWriter, value: Date?) {
            if (value != null) {
                writer.value(dateFormat.format(value))
            }
        }
    }
}
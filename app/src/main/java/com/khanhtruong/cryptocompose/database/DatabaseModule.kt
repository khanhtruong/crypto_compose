package com.khanhtruong.cryptocompose.database

import android.content.Context
import androidx.room.Room
import com.khanhtruong.cryptocompose.database.config.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    fun provideDB(@ApplicationContext context: Context): DatabaseRepo {
        return synchronized(this) {
            DatabaseRepo(
                Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseService::class.java,
                    Constants.DATABASE_NAME
                ).build()
            )
        }
    }
}
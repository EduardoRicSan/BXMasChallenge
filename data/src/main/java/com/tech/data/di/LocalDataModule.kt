package com.tech.data.di

import android.content.Context
import androidx.room.Room
import com.tech.data.local.AppDatabase
import com.tech.data.local.dao.BXMasDao
import com.tech.data.local.dataSource.BXMasLocalDataSource
import com.tech.data.local.dataSource.BXMasLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    fun provideBXMasDao(database: AppDatabase) = database.bXMasDao()

    @Provides
    @Singleton
    fun provideBXMasLocalDataSource(
        bxMasDao: BXMasDao
    ): BXMasLocalDataSource = BXMasLocalDataSourceImpl(bxMasDao)
}
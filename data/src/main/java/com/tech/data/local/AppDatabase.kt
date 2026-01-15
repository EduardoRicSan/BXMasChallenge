package com.tech.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tech.data.local.dao.BXMasDao
import com.tech.data.local.entity.PhotoEntity

/**
Room database for the application, holding all entities related to expenses.
Provides access to DAOs for performing database operations.
 */
@Database(entities = [PhotoEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bXMasDao(): BXMasDao
}
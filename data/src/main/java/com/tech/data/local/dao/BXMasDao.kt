package com.tech.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tech.data.local.entity.PhotoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BXMasDao {

    @Query("SELECT * FROM photos WHERE id = :photoId LIMIT 1")
    fun getPhotoById(photoId: Int): Flow<PhotoEntity?>

    @Query("""
        SELECT * FROM photos
        ORDER BY id
        LIMIT :limit OFFSET :offset
    """)
    suspend fun getPagedPhotos(
        limit: Int,
        offset: Int
    ): List<PhotoEntity>

    @Query("SELECT COUNT(*) FROM photos")
    suspend fun count(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(photos: List<PhotoEntity>)



    @Query("DELETE FROM photos")
    suspend fun clear()
}
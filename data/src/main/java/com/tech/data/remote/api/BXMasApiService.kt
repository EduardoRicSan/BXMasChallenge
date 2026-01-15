package com.tech.data.remote.api

import com.tech.data.remote.dto.PhotoDTO

interface BXMasApiService {
    suspend fun getPhotos(): List<PhotoDTO>
}
package com.tech.data.remote.api

import com.tech.data.remote.api.BXMasApiConstants.BXMAS_GET_PHOTOS_ENDPOINT
import com.tech.data.remote.dto.PhotoDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class BXMasApiServiceImpl (
    private val bXMasHttpClient: HttpClient,
) : BXMasApiService {

    override suspend fun getPhotos(): List<PhotoDTO> {
        return bXMasHttpClient.get(BXMAS_GET_PHOTOS_ENDPOINT).body()
    }


}
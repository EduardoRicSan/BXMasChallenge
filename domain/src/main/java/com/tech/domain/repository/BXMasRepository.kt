package com.tech.domain.repository

import com.tech.core.remote.NetworkResult
import com.tech.domain.model.PhotoUIModel
import kotlinx.coroutines.flow.Flow

interface BXMasRepository {

    /**
     * Sincroniza las fotos desde la fuente remota y las persiste localmente.
     * La UI solo necesita saber si la operación está cargando, fue exitosa o falló.
     */
    suspend fun syncPhotos(): Flow<NetworkResult<Unit>>

    /**
     * Obtiene una página de fotos desde la fuente local (Room).
     *
     * @param page índice de página (0-based)
     * @param pageSize número de elementos por página
     */
    suspend fun getPagedPhotos(
        page: Int,
        pageSize: Int
    ): List<PhotoUIModel>

    /**
     * Total de fotos disponibles localmente.
     * Usado para saber si aún hay más páginas por cargar.
     */
    suspend fun getTotalPhotos(): Int
}
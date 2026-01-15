package com.tech.core.network

import com.tech.core.remote.NetworkResult

inline fun <T, R> NetworkResult<List<T>>.mapList(
    mapper: (T) -> R
): NetworkResult<List<R>> =
    when (this) {
        is NetworkResult.Success ->
            NetworkResult.Success(data.map(mapper))
        is NetworkResult.Error ->
            NetworkResult.Error(message, code)
        NetworkResult.Loading ->
            NetworkResult.Loading
    }
package com.tech.core.network

import com.tech.core.remote.NetworkResult
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.SerializationException
import java.io.IOException

fun Throwable.toNetworkError(): NetworkResult.Error {
    return when (this) {

        is ClientRequestException -> mapClientError(this)

        is ServerResponseException -> mapServerError(this)

        is RedirectResponseException -> NetworkResult.Error(
            message = "Unexpected redirect",
            code = response.status.value
        )

        is IOException -> NetworkResult.Error(
            message = "No internet connection"
        )

        is SerializationException -> NetworkResult.Error(
            message = "Error parsing server response"
        )

        else -> NetworkResult.Error(
            message = message ?: "Unknown error"
        )
    }
}

private fun mapClientError(
    exception: ClientRequestException
): NetworkResult.Error {
    return when (exception.response.status) {

        HttpStatusCode.BadRequest -> NetworkResult.Error(
            message = "Bad request",
            code = 400
        )

        HttpStatusCode.Unauthorized -> NetworkResult.Error(
            message = "Unauthorized",
            code = 401
        )

        HttpStatusCode.Forbidden -> NetworkResult.Error(
            message = "Forbidden",
            code = 403
        )

        HttpStatusCode.NotFound -> NetworkResult.Error(
            message = "Resource not found",
            code = 404
        )

        HttpStatusCode.TooManyRequests -> NetworkResult.Error(
            message = "Too many requests",
            code = 429
        )

        else -> NetworkResult.Error(
            message = "Client error ${exception.response.status.value}",
            code = exception.response.status.value
        )
    }
}

private fun mapServerError(
    exception: ServerResponseException
): NetworkResult.Error {
    return when (exception.response.status) {

        HttpStatusCode.InternalServerError -> NetworkResult.Error(
            message = "Internal server error",
            code = 500
        )

        HttpStatusCode.BadGateway -> NetworkResult.Error(
            message = "Bad gateway",
            code = 502
        )

        HttpStatusCode.ServiceUnavailable -> NetworkResult.Error(
            message = "Service unavailable",
            code = 503
        )

        HttpStatusCode.GatewayTimeout -> NetworkResult.Error(
            message = "Gateway timeout",
            code = 504
        )

        else -> NetworkResult.Error(
            message = "Server error ${exception.response.status.value}",
            code = exception.response.status.value
        )
    }
}



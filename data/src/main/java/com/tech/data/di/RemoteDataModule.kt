package com.tech.data.di

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import com.tech.data.BuildConfig
import com.tech.data.local.dataSource.BXMasLocalDataSource
import com.tech.data.remote.api.BXMasApiService
import com.tech.data.remote.api.BXMasApiServiceImpl
import com.tech.data.remote.dataSource.BXMasRemoteDataSource
import com.tech.data.remote.dataSource.BXMasRemoteDataSourceImpl
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient = HttpClient(Android) {
        install(Logging) {
            if (BuildConfig.DEBUG) logger = object : Logger {
                override fun log(message: String) {
                    Log.d("AccountClient", message)
                }
            }
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                }
            )
        }
        defaultRequest {
            url(BuildConfig.BASE_URL)
            url {
                protocol = URLProtocol.HTTPS
            }
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }
    }


    @Provides
    @Singleton
    fun provideBXMasApiService(
        bXMasHttpClient: HttpClient,
    ): BXMasApiService =
        BXMasApiServiceImpl(bXMasHttpClient)

    @Provides
    @Singleton
    fun provideBXMasDataSource(
        bXMasApiService: BXMasApiService,
        bXMasLocalDataSource: BXMasLocalDataSource,
    ): BXMasRemoteDataSource =
        BXMasRemoteDataSourceImpl(
            bXMasApiService,
            bXMasLocalDataSource
        )

}
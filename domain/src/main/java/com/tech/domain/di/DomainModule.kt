package com.tech.domain.di

import com.tech.data.local.dataSource.BXMasLocalDataSource
import com.tech.data.remote.dataSource.BXMasRemoteDataSource
import com.tech.domain.repository.BXMasRepository
import com.tech.domain.repository.BXMasRepositoryImpl
import com.tech.domain.useCase.GetPagedPhotosUseCase
import com.tech.domain.useCase.SyncPhotosUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideBXMasRepository(
        bxMasRemoteDataSource: BXMasRemoteDataSource,
        bxMasLocalDataSource: BXMasLocalDataSource,
    ): BXMasRepository = BXMasRepositoryImpl(bxMasRemoteDataSource, bxMasLocalDataSource)


    @Provides
    @Singleton
    fun provideGetPhotosUseCase(
        bxMasRepository: BXMasRepository,
    ): GetPagedPhotosUseCase = GetPagedPhotosUseCase(bxMasRepository)

    @Provides
    @Singleton
    fun provideSyncPhotosUseCase(
        bxMasRepository: BXMasRepository,
    ): SyncPhotosUseCase = SyncPhotosUseCase(bxMasRepository)


}
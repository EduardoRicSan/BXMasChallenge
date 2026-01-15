package com.tech.domain.di

import com.tech.data.remote.dataSource.BXMasRemoteDataSource
import com.tech.domain.repository.BXMasRepository
import com.tech.domain.repository.BXMasRepositoryImpl
import com.tech.domain.useCase.GetPhotosUseCase
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
    ): BXMasRepository = BXMasRepositoryImpl(bxMasRemoteDataSource)


    @Provides
    @Singleton
    fun provideGetPhotosUseCase(
        bxMasRepository: BXMasRepository,
    ): GetPhotosUseCase = GetPhotosUseCase(bxMasRepository)


}
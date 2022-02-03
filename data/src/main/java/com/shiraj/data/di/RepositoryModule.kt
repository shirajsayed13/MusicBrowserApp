package com.shiraj.data.di

import com.shiraj.data.repository.AlbumRepositoryImpl
import com.shiraj.domain.repository.AlbumRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    internal abstract fun bindAlbumRepository(repositoryImpl: AlbumRepositoryImpl): AlbumRepository
}
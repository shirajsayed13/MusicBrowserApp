package com.shiraj.domain.di

import com.shiraj.domain.usecase.AlbumUseCase
import com.shiraj.domain.usecase.AlbumUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    @Singleton
    internal abstract fun bindAlbumUseCase(useCaseImpl: AlbumUseCaseImpl): AlbumUseCase
}
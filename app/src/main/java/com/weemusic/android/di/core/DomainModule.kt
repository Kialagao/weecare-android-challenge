package com.weemusic.android.di.core

import com.weemusic.android.domain.usecases.GetSortedAlbumsUseCase
import com.weemusic.android.domain.usecases.GetTopAlbumsUseCase
import com.weemusic.android.domain.usecases.UpdateTopAlbumsUseCase
import com.weemusic.android.repository.MainRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Provides
    @Singleton
    fun provideGetTopAlbumsUseCase(mainRepository: MainRepository) =
        GetTopAlbumsUseCase(mainRepository)

    @Provides
    @Singleton
    fun provideGetSortedAlbumsUseCase(mainRepository: MainRepository) =
        GetSortedAlbumsUseCase(mainRepository)

    @Provides
    @Singleton
    fun provideUpdateTopAlbumsUseCase(mainRepository: MainRepository) =
        UpdateTopAlbumsUseCase(mainRepository)
}
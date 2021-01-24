package com.weemusic.android.di.core

import com.weemusic.android.domain.usecases.GetSortedAlbumsUseCase
import com.weemusic.android.domain.usecases.GetTopAlbumsUseCase
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
    fun provideGetSortedAlbumsuseCase(mainRepository: MainRepository) =
        GetSortedAlbumsUseCase(mainRepository)
}
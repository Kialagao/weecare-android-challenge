package com.weemusic.android.di.core

import com.weemusic.android.domain.GetTopAlbumsUseCase
import com.weemusic.android.network.services.iTunesApi
import com.weemusic.android.repository.MainRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Provides
    @Singleton
    fun getTopAlbumsUseCase(mainRepository: MainRepository) =
        GetTopAlbumsUseCase(mainRepository)
}
package com.weemusic.android.di.main

import com.weemusic.android.domain.usecases.GetSortedAlbumsUseCase
import com.weemusic.android.domain.usecases.GetTopAlbumsUseCase
import com.weemusic.android.ui.MainViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @MainScope
    @Provides
    fun provideMainViewModelFactory(
        getTopAlbumsUseCase: GetTopAlbumsUseCase,
        getSortedAlbumsUseCase: GetSortedAlbumsUseCase
    ) : MainViewModelFactory {
        return MainViewModelFactory(getTopAlbumsUseCase, getSortedAlbumsUseCase)
    }
}
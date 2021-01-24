package com.weemusic.android.di.main

import com.weemusic.android.domain.GetTopAlbumsUseCase
import com.weemusic.android.ui.MainViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @MainScope
    @Provides
    fun provideMainViewModelFactory(
        getTopAlbumsUseCase: GetTopAlbumsUseCase
    ) : MainViewModelFactory {
        return MainViewModelFactory(getTopAlbumsUseCase)
    }
}
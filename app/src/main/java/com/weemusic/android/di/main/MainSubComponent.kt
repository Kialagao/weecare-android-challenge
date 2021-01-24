package com.weemusic.android.di.main

import com.weemusic.android.ui.MainActivity
import dagger.Subcomponent

@MainScope
@Subcomponent(modules = [MainModule::class])
interface MainSubComponent {
    fun inject(mainActivity: MainActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create() : MainSubComponent
    }
}
package com.weemusic.android.di

import com.weemusic.android.di.main.MainSubComponent

interface Injector {

    fun createMainSubComponent() : MainSubComponent
}
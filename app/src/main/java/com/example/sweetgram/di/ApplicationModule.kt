package com.example.sweetgram.di

import android.content.Context
import com.example.sweetgram.SweetgramApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {
    @Singleton
    @Provides
    fun getApplication(): SweetgramApplication{
        return SweetgramApplication.instance
    }

    @Singleton
    @Provides
    fun getApplicationContext(): Context {
        return SweetgramApplication.instance
    }
}
package com.example.sweetgram.di

import android.content.Context
import android.content.SharedPreferences
import com.example.sweetgram.R
import dagger.Module
import dagger.Provides

@Module
class PreferencesModule {
    @Provides
    fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(context.resources.getString(R.string.config_file_name), Context.MODE_PRIVATE)
    }
}
package com.example.sweetgram

import android.app.Application
import com.example.sweetgram.di.DaggerMainComponent
import com.example.sweetgram.di.MainComponent

class SweetgramApplication: Application() {
    companion object {
        lateinit var instance: SweetgramApplication
    }

    lateinit var injector: MainComponent

    override fun onCreate() {
        super.onCreate()

        instance = this

        injector = DaggerMainComponent.create()
    }
}
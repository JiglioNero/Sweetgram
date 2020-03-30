package com.example.sweetgram.di

import com.example.sweetgram.SweetgramApplication
import com.example.sweetgram.ui.activities.login.LoginActivity
import com.example.sweetgram.ui.activities.login.LoginViewModel
import com.example.sweetgram.ui.event_lenta.EventLentViewModel
import com.example.sweetgram.ui.event_redactor.EventRedactorViewModel
import com.example.sweetgram.ui.statistic.StatisticViewModel
import dagger.Component
import jiglionero.android.app.putonpompom.di.DataModule
import jiglionero.android.app.putonpompom.di.RecyclerModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class,
    DataModule::class,
    RecyclerModule::class,
    PreferencesModule::class])
interface MainComponent {
    fun inject(obj: EventLentViewModel)
    fun inject(obj: SweetgramApplication)
    fun inject(obj: StatisticViewModel)
    fun inject(obj: EventRedactorViewModel)
    fun inject(obj: LoginViewModel)
    fun inject(obj: LoginActivity)
}
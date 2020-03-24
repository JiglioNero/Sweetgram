package com.example.sweetgram.di

import com.example.sweetgram.ui.event_lenta.EventLentViewModel
import dagger.Component
import jiglionero.android.app.putonpompom.di.DataModule
import jiglionero.android.app.putonpompom.di.RecyclerModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    DataModule::class,
    RecyclerModule::class])
interface MainComponent {
    fun inject(eventLentViewModel: EventLentViewModel)
    //fun inject(weatherPerHourForecastViewModel: WeatherPerHourForecastViewModel)
}
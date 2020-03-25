package com.example.sweetgram.di

import com.example.sweetgram.ui.event_lenta.EventLentViewModel
import com.example.sweetgram.ui.event_redactor.EventRedactorViewModel
import dagger.Component
import jiglionero.android.app.putonpompom.di.DataModule
import jiglionero.android.app.putonpompom.di.RecyclerModule
import jiglionero.android.app.putonpompom.view.recycler.DatingEventPositionalDataSource
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class,
    DataModule::class,
    RecyclerModule::class])
interface MainComponent {
    fun inject(eventLentViewModel: EventLentViewModel)
    fun inject(eventRedactorViewModel: EventRedactorViewModel)
    fun inject(datingEventPositionalDataSource: DatingEventPositionalDataSource)
}
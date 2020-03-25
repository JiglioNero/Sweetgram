package com.example.sweetgram.ui.event_redactor

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.sweetgram.SweetgramApplication
import com.example.sweetgram.data.DataNode
import com.example.sweetgram.data.entitys.DatingEvent
import javax.inject.Inject

class EventRedactorViewModel : ViewModel() {
    @Inject
    lateinit var dataNode: DataNode

    val datingEventField =  ObservableField<DatingEvent>()

    init {
        SweetgramApplication.instance.injector.inject(this)
    }
}

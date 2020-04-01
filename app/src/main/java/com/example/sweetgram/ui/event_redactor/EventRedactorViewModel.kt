package com.example.sweetgram.ui.event_redactor

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.sweetgram.SweetgramApplication
import com.example.sweetgram.data.DataNode
import com.example.sweetgram.data.entitys.DatingEvent
import com.example.sweetgram.data.entitys.EventType
import com.example.sweetgram.data.entitys.Relationship
import com.example.sweetgram.ui.activities.MainActivity
import javax.inject.Inject

class EventRedactorViewModel : ViewModel() {
    @Inject
    lateinit var dataNode: DataNode
    var relationship: Relationship
    val datingEventField =  ObservableField<DatingEvent>()

    var eventTypes: List<EventType>

    init {
        SweetgramApplication.instance.injector.inject(this)
        eventTypes = dataNode.getAllEventTypes()
        relationship = dataNode.getRelationshipByLoversIds(MainActivity.user.id)!!
    }
}

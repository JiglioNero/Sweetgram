package com.example.sweetgram.data

import androidx.lifecycle.LiveData
import com.example.sweetgram.data.entitys.DatingEvent

class DataNode(
    private val database: Database
) {

    fun saveDatingEvent(vararg events :DatingEvent){
        database.saveDatingEvent(events)
    }

    fun getDatingEvents(filter: String = ""): LiveData<List<DatingEvent>>{
        return if (filter.isBlank())
            database.getDatingEventsWithFilter(filter)
        else
            database.getAllDatingEvents()
    }

    fun deleteDatingEvent(event: DatingEvent){
        database.deleteDatingEvent(event)
    }

}
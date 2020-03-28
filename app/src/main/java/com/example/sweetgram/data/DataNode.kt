package com.example.sweetgram.data

import androidx.lifecycle.LiveData
import com.example.sweetgram.data.entitys.DatingEvent

class DataNode(
    private val database: Database
) {
    fun saveDatingEvent(vararg events :DatingEvent){
        database.saveDatingEvent(events)
    }

    fun getDatingEvents(filter: String? = ""): LiveData<List<DatingEvent>>{
        return if (filter.isNullOrBlank())
                database.getAllDatingEvents()
        else
            database.getDatingEventsWithFilter(filter)
    }

    fun getDatingEventById(id: Long): DatingEvent{
        return database.getDatingEventById(id)
    }

    fun deleteDatingEvent(id: Long){
        database.deleteDatingEvent(id)
    }

}
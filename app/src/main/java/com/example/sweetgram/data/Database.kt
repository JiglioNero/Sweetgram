package com.example.sweetgram.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sweetgram.data.dao.DatingEventDao
import com.example.sweetgram.data.entitys.DatingEvent

@Database(entities = [DatingEvent::class], version = 1)
abstract class Database: RoomDatabase()  {
    abstract fun datingEventDao(): DatingEventDao

    fun saveDatingEvent(events: Array<out DatingEvent>){
        /*val sb = StringBuilder()
        events.forEach { sb.append("\n${it.toString()}\n") }
        Log.e("Database", "$sb save" )*/
        datingEventDao().insert(events.asList())
    }

    fun getAllDatingEvents(): LiveData<List<DatingEvent>> {
        Log.e("Database", "get all dating events (all) ${datingEventDao().getAll().value}" )
        return datingEventDao().getAll()
    }

    fun getDatingEventsWithFilter(filter: String): LiveData<List<DatingEvent>> {
        Log.e("Database", "get all dating events ${datingEventDao().getAllWithFilter("%$filter%").value}" )
        return datingEventDao().getAllWithFilter("%$filter%")
    }

    fun deleteDatingEvent(id: Long) {
        Log.e("Database", "delete with id = $id" )
        datingEventDao().deleteById(id)
    }

    @Synchronized fun doInBackground(operation: () -> Any){
        Thread {operation()}.start()
    }
}
package com.example.sweetgram.data

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sweetgram.data.dao.DatingEventDao
import com.example.sweetgram.data.entitys.DatingEvent

@Database(entities = [DatingEvent::class], version = 1)
abstract class Database: RoomDatabase()  {
    abstract fun datingEventDao(): DatingEventDao

    @Synchronized fun saveDatingEvent(events: Array<out DatingEvent>){
        object: AsyncTask<Void, Void, Void>(){
            override fun doInBackground(vararg params: Void?): Void? {
                datingEventDao().insert(events.asList())
                return null
            }

        }.execute()
    }

    fun getAllDatingEvents(): LiveData<List<DatingEvent>> {
        return datingEventDao().getAll()
    }

    fun getDatingEventsWithFilter(filter: String): LiveData<List<DatingEvent>> {
        return datingEventDao().getAllWithFilter("%$filter%")
    }

    fun deleteDatingEvent(event: DatingEvent) {
        datingEventDao().delete(event)
    }
}
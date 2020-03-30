package com.example.sweetgram.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sweetgram.data.dao.DatingEventDao
import com.example.sweetgram.data.dao.EventTypeDao
import com.example.sweetgram.data.dao.LoverDao
import com.example.sweetgram.data.dao.RelationshipDao
import com.example.sweetgram.data.entitys.DatingEvent
import com.example.sweetgram.data.entitys.EventType
import com.example.sweetgram.data.entitys.Lover
import com.example.sweetgram.data.entitys.Relationship

@Database(entities = [DatingEvent::class, EventType::class, Lover::class, Relationship::class], version = 1)
abstract class Database: RoomDatabase()  {
    abstract fun datingEventDao(): DatingEventDao
    abstract fun eventTypeDao(): EventTypeDao
    abstract fun loverDao(): LoverDao
    abstract fun relationshipDao(): RelationshipDao

    fun filterTransform(filter: String): String{
        return "%$filter%"
    }
}
package com.example.sweetgram.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.sweetgram.data.entitys.EventType

@Dao
interface EventTypeDao {
    @Query("SELECT * FROM EventType")
    fun getAll(): List<EventType>

    @Query("SELECT COUNT(*) FROM DatingEvent WHERE DatingEvent.eventType == :eventName AND :currentDt - DatingEvent.dt <= :period")
    fun getCountEventsByPeriod(eventName: String, period: Long, currentDt: Long): Int

    @Insert
    fun addType(type: EventType)

    @Delete
    fun deleteType(type: EventType)
}
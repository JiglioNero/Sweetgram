package com.example.sweetgram.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.sweetgram.data.entitys.DatingEvent

@Dao
interface DatingEventDao {
    @Query("SELECT * FROM DatingEvent ORDER BY DatingEvent.dt DESC")
    fun getAll(): LiveData<List<DatingEvent>>


    @Query("SELECT * FROM DatingEvent WHERE DatingEvent.relId is :id AND (DatingEvent.location LIKE :filter OR DatingEvent.dt_txt LIKE :filter OR DatingEvent.eventType LIKE :filter OR DatingEvent.eventText LIKE :filter) ORDER BY DatingEvent.dt DESC")
    fun getWithFilterByUserId(id: Long, filter: String): LiveData<List<DatingEvent>>

    @Query("SELECT * FROM DatingEvent WHERE DatingEvent.relId is :id ORDER BY DatingEvent.dt DESC")
    fun getByUserId(id: Long): LiveData<List<DatingEvent>>

    @Query("SELECT * FROM DatingEvent WHERE DatingEvent.id is :id")
    fun getById(id: Long): DatingEvent

    @Query("DELETE FROM DatingEvent WHERE DatingEvent.id is :id")
    fun deleteById(id: Long)

    @Query(
        "SELECT * " +
                "FROM DatingEvent " +
                "WHERE DatingEvent.location LIKE :filter OR " +
                "DatingEvent.dt_txt LIKE :filter OR " +
                "DatingEvent.eventType LIKE :filter OR " +
                "DatingEvent.eventText LIKE :filter " +
                "ORDER BY DatingEvent.dt DESC"
    )
    fun getAllWithFilter(filter: String): LiveData<List<DatingEvent>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entities: List<DatingEvent>): Array<Long>

    @Delete
    fun delete(entity: DatingEvent)
}
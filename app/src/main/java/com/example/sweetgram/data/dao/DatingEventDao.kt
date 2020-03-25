package com.example.sweetgram.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.sweetgram.data.entitys.DatingEvent

@Dao
interface DatingEventDao {
    @Query("SELECT * FROM DatingEvent ORDER BY DatingEvent.dt DESC")
    fun getAll(): LiveData<List<DatingEvent>>

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
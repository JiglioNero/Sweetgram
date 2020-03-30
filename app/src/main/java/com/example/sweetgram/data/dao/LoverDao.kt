package com.example.sweetgram.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.sweetgram.data.entitys.Lover

@Dao
interface LoverDao {

    @Query("SELECT * FROM Lover")
    fun getAll(): LiveData<List<Lover>>

    @Query("SELECT * FROM Lover WHERE Lover.id is :id")
    fun getById(id: Long): Lover

    @Query("SELECT * FROM Lover WHERE Lover.username == :username")
    fun getByCredentials(username: String): Lover?

    @Query("DELETE FROM Lover WHERE Lover.id is :id")
    fun deleteById(id: Long)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(entity: Lover): Long

    @Delete
    fun delete(entity: Lover)
}
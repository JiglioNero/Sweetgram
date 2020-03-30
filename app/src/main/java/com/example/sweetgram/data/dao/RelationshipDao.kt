package com.example.sweetgram.data.dao

import androidx.room.*
import com.example.sweetgram.data.entitys.Relationship

@Dao
interface RelationshipDao{
    @Query("SELECT * FROM Relationship")
    fun getAll(): List<Relationship>

    @Query("SELECT * FROM Relationship WHERE (Relationship.lover1Id == :lover1Id AND Relationship.lover2Id == :lover2Id) OR (Relationship.lover1Id == :lover2Id AND Relationship.lover2Id == :lover1Id)")
    fun getByLovers(lover1Id: Long, lover2Id: Long): Relationship?

    @Query("SELECT * FROM Relationship WHERE Relationship.lover1Id == :loverId OR Relationship.lover2Id == :loverId")
    fun getByOneOfLovers(loverId: Long): Relationship?

    @Query("SELECT * FROM Relationship WHERE Relationship.id is :id")
    fun getById(id: Long): Relationship

    @Query("DELETE FROM Relationship WHERE Relationship.id is :id")
    fun deleteById(id: Long)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(entity: Relationship): Long

    @Delete
    fun delete(entity: Relationship)
}

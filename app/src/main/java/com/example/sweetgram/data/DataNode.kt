package com.example.sweetgram.data

import androidx.lifecycle.LiveData
import com.example.sweetgram.data.entitys.*

class DataNode(
    private val database: Database
) {

    fun getAllRelationship(): List<Relationship>{
        return database.relationshipDao().getAll()
    }

    fun getRelationshipByLoversIds(lover1Id: Long, lover2Id: Long = -1): Relationship?{
        return if (lover2Id <= 0)
            database.relationshipDao().getByOneOfLovers(lover1Id)
        else
            database.relationshipDao().getByLovers(lover1Id, lover2Id)
    }

    fun getRelationshipById(id: Long): Relationship{
        return database.relationshipDao().getById(id)
    }

    fun deleteRelationship(relationship: Relationship){
        database.relationshipDao().delete(relationship)
    }

    fun saveRelationship(relationship: Relationship): Long{
        return database.relationshipDao().insert(relationship)
    }

    fun getLoverById(id: Long){
        database.loverDao().getById(id)
    }

    fun getLoverByCredentials(credentials: LoverCredentials): Lover?{
        return database.loverDao().getByCredentials(credentials.username)
    }

    fun deleteLover(user: Lover){
        database.loverDao().delete(user)
    }

    fun saveLover(user: Lover): Long{
        return database.loverDao().insert(user)
    }

    fun saveDatingEvent(vararg events :DatingEvent){
        database.datingEventDao().insert(events.asList())
    }

    fun getDatingEvents(filter: String? = ""): LiveData<List<DatingEvent>>{
        return if (filter.isNullOrBlank())
                database.datingEventDao().getAll()
        else
            database.datingEventDao().getAllWithFilter(filter)
    }

    fun getDatingEventById(id: Long): DatingEvent{
        return database.datingEventDao().getById(id)
    }

    fun deleteDatingEvent(id: Long){
        database.datingEventDao().deleteById(id)
    }

    fun getAllEventTypes(): List<EventType>{
        return database.eventTypeDao().getAll()
    }

    fun deleteEventType(type: EventType){
        return database.eventTypeDao().deleteType(type)
    }

    fun addEventType(type: EventType){
        return database.eventTypeDao().addType(type)
    }

    fun getCountEventsByPeriod(period: Long, currentDt: Long): List<Pair<EventType, Int>>{
        val pairsList = arrayListOf<Pair<EventType, Int>>()
        database.eventTypeDao().getAll().forEach {
            pairsList.add(Pair(it, database.eventTypeDao().getCountEventsByPeriod(it.name, period, currentDt)))
        }
        return pairsList
    }

}
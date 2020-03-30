package com.example.sweetgram.ui.statistic

import androidx.lifecycle.ViewModel
import com.example.sweetgram.SweetgramApplication
import com.example.sweetgram.data.DataNode
import com.example.sweetgram.data.entitys.EventType
import com.example.sweetgram.data.entitys.Relationship
import com.example.sweetgram.ui.statistic.recycler.StatisticItemAdapter
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class StatisticViewModel : ViewModel() {
    @Inject
    lateinit var dataNode: DataNode
    var relationship: Relationship

    var eventTypes: List<EventType>
    var adapter = StatisticItemAdapter()

    init{
        SweetgramApplication.instance.injector.inject(this)
        eventTypes = dataNode.getAllEventTypes()
        relationship = dataNode.getAllRelationship()[0]
    }

    fun getCountOfDaysBeforeAnniversary(): Int{
        val currentDt = Date().time
        val timeBefore = (currentDt - relationship.dt) % (86400000L * 365)
        return ((86400000L * 365 - timeBefore)/86400000L).toInt()
    }

    fun setStatisticByPeriod(periodId: Int){
        adapter.listOfPairs = ArrayList(dataNode.getCountEventsByPeriod(Periods.map[periodId]!!, Date().time))
        adapter.notifyDataSetChanged()
    }

    fun getCountOfPeriodsOfRelationship(periodId: Int): Int{
        val period = Periods.map[periodId]!!
        val currentDt = Date().time
        return ((currentDt - relationship.dt)/period).toInt()
    }

}
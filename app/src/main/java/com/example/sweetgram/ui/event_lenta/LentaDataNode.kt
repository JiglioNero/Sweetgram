package com.example.sweetgram.ui.event_lenta

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.sweetgram.data.DataNode
import com.example.sweetgram.data.entitys.DatingEvent
import com.example.sweetgram.ui.activities.MainActivity

class LentaDataNode(val dataNode: DataNode) {

    var relId = dataNode.getRelationshipByLoversIds(MainActivity.user.id)!!.id
    var filter = MutableLiveData("")
    var actualEventList = dataNode.getDatingEventsByRelationshipId(relId, filter.value)


    fun initObserveResponse(lifeCycleOwner: LifecycleOwner, pagedList: LiveData<PagedList<DatingEvent>>) {
        relId = dataNode.getRelationshipByLoversIds(MainActivity.user.id)!!.id
        Log.d("LentaDataNode", "username = ${MainActivity.user.username} relId = $relId")
        filter.observe(lifeCycleOwner, Observer {
            actualEventList = dataNode.getDatingEventsByRelationshipId(relId, it)
            actualEventList.observe(lifeCycleOwner, Observer{
                pagedList.value?.dataSource?.invalidate()
            })
            Log.d("LentaDataNodeINFILTER", "events = ${actualEventList.value}")
        })
        actualEventList.observe(lifeCycleOwner, Observer{
            pagedList.value?.dataSource?.invalidate()
        })
        Log.d("LentaDataNode", "events = ${actualEventList.value}")
    }
}